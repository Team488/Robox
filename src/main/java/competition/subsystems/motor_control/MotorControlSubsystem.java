package competition.subsystems.motor_control;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.revrobotics.CANSparkMax;
import competition.electrical_contract.ElectricalContract;
import competition.subsystems.drive.DriveSubsystem;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANSparkMax;
import xbot.common.controls.actuators.XCANSparkMaxPIDProperties;
import xbot.common.properties.DoubleProperty;
import xbot.common.properties.PropertyFactory;

@Singleton
public class MotorControlSubsystem extends BaseSubsystem {

    boolean listeningForLeft = false;
    boolean listeningForRight = false;

    XCANSparkMax activeLeft;
    XCANSparkMax activeRight;

    XCANSparkMax[] motors;

    DoubleProperty leftMotorVelocity;
    DoubleProperty leftMotorPosition;
    DoubleProperty rightMotorVelocity;
    DoubleProperty rightMotorPosition;

    @Inject
    public MotorControlSubsystem(XCANSparkMax.XCANSparkMaxFactory smFactory, PropertyFactory propertyFactory, ElectricalContract contract, DriveSubsystem ds) {
        propertyFactory.setPrefix(this);

        XCANSparkMaxPIDProperties properties = new XCANSparkMaxPIDProperties();
        XCANSparkMax motor1 = smFactory.create(contract.getMotor1(), this.getPrefix(), "m1", "m1", properties);
        XCANSparkMax motor3 = smFactory.create(contract.getMotor3(), this.getPrefix(), "m3", "m3", properties);
        XCANSparkMax motor4 = smFactory.create(contract.getMotor4(), this.getPrefix(), "m4", "m4", properties);
        XCANSparkMax motor5 = smFactory.create(contract.getMotor5(), this.getPrefix(), "m5", "m5", properties);

        /*leftMotorPosition = propertyFactory.createEphemeralProperty("LeftMotorPosition", 0.0);
        leftMotorVelocity = propertyFactory.createEphemeralProperty("LeftMotorVelocity", 0.0);
        rightMotorPosition = propertyFactory.createEphemeralProperty("RightMotorPosition", 0.0);
        rightMotorVelocity = propertyFactory.createEphemeralProperty("RightMotorVelocity", 0.0);*/

        motors = new XCANSparkMax[]{motor1, motor3, motor4, motor5};
        updateMotor(motor4, motor1);


    }

    public void updateMotor(XCANSparkMax left, XCANSparkMax right) {
        this.activeLeft = left;
        this.activeRight = right;
    }

    public void setMotor(int index) {
        // Check if index is in range of motors array
        if (index+1 > motors.length || index < 0) {return;}

        if (listeningForLeft) {
            // Swap left and right if setting right to left
            if (activeRight == motors[index]) {
                updateMotor(motors[index], activeLeft);
                return;
            }
            updateMotor(motors[index], activeRight);

        } else if (listeningForRight) {
            if (activeLeft == motors[index]) {
                updateMotor(activeRight, motors[index]);
                return;
            }
            updateMotor(activeLeft, motors[index]);
        }
    }

    public void listenForRight() {
        listeningForRight = !listeningForRight;
    }

    public void listenForLeft() {
        listeningForLeft = !listeningForLeft;
    }

    public void driveActive(double leftPower, double rightPower) {
        activeLeft.set(leftPower);
        activeRight.set(rightPower);
    }

    public void setLeftSpeed(double speed) {
        activeLeft.setReference(speed, CANSparkMax.ControlType.kVelocity);

    }
    public void setLeftPosition(double position) {
        activeLeft.setReference(position, CANSparkMax.ControlType.kPosition);

    }

    public void setRightSpeed(double speed) {
        //System.out.println("activeright running with speed: "+speed);
        activeRight.setReference(speed, CANSparkMax.ControlType.kVelocity);

    }
    public void setRightPosition(double position) {
        activeRight.setReference(position, CANSparkMax.ControlType.kPosition);
    }

    @Override
    public void periodic() {
        for (XCANSparkMax motor : motors) {
            motor.refreshDataFrame();
            motor.periodic();
        }

        activeLeft.refreshDataFrame();
        activeRight.refreshDataFrame();
        /*leftMotorVelocity.set(activeLeft.getVelocity());
        leftMotorPosition.set(activeLeft.getPosition());
        rightMotorVelocity.set(activeRight.getVelocity());
        rightMotorPosition.set(activeRight.getPosition());*/

        /*System.out.println("motors[0]"+motors[0].getVelocity());
        System.out.println("activeRight "+activeRight.getVelocity());
        System.out.println("motors[3]"+motors[3].getVelocity());
        System.out.println("activeLeft "+activeLeft.getVelocity());*/
       // activeLeft.setP(.0007);
       // activeRight.setP(.0007);
        System.out.println("activeLeft.getP() %e "+(String.format("%e",activeLeft.getP())));
        System.out.println("activeRight.getP() "+(String.format("%e", activeRight.getP())));
    }
}
