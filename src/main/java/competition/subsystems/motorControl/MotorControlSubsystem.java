package competition.subsystems.motorControl;

import javax.inject.Inject;

import competition.electrical_contract.ElectricalContract;
import competition.subsystems.drive.DriveSubsystem;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANSparkMax;
import xbot.common.properties.IPropertySupport;
import xbot.common.properties.PropertyFactory;


public class MotorControlSubsystem extends BaseSubsystem {



    final DriveSubsystem drive;

    boolean listeningForLeft = false;
    boolean listeningForRight = false;

    XCANSparkMax[] motors;

    @Inject
    public MotorControlSubsystem(XCANSparkMax.XCANSparkMaxFactory smFactory, PropertyFactory propertyFactory, ElectricalContract contract, DriveSubsystem ds) {
        propertyFactory.setPrefix(this);
        this.drive = ds;


        XCANSparkMax motor1 = smFactory.create(contract.getMotor1(), this.getPrefix(), "m1");
        XCANSparkMax motor2 = smFactory.create(contract.getMotor2(), this.getPrefix(), "m2");
        XCANSparkMax motor3 = smFactory.create(contract.getMotor3(), this.getPrefix(), "m3");
        XCANSparkMax motor4 = smFactory.create(contract.getMotor4(), this.getPrefix(), "m4");
        XCANSparkMax motor5 = smFactory.create(contract.getMotor5(), this.getPrefix(), "m5");

        motors = new XCANSparkMax[]{motor1, motor2, motor3, motor4, motor5};
        updateMotor(motor1, motor2);
    }

    public void updateMotor(XCANSparkMax left, XCANSparkMax right) {
        drive.leftMotor = left;
        drive.rightMotor = right;
    }

    public void setMotor(int index) {
        if (motors[index] != null) {
            if (listeningForLeft) {
                if (drive.rightMotor == motors[index]) {
                    updateMotor(motors[index], drive.leftMotor);
                    return;
                }
                updateMotor(motors[index], drive.rightMotor);
            } else if (listeningForRight) {
                if (drive.leftMotor == motors[index]) {
                    updateMotor(drive.rightMotor, motors[index]);
                    return;
                }
                updateMotor(drive.leftMotor, motors[index]);
            }
        }
    }

    public void listenForRight() {
        listeningForRight = !listeningForRight;
    }

    public void listenForLeft() {
        listeningForLeft = !listeningForLeft;
    }
}
