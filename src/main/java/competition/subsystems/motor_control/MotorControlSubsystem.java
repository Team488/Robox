package competition.subsystems.motor_control;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.revrobotics.CANSparkMax;
import competition.electrical_contract.ElectricalContract;
import competition.subsystems.drive.DriveSubsystem;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANSparkMax;
import xbot.common.properties.DoubleProperty;
import xbot.common.properties.PropertyFactory;

@Singleton
public class MotorControlSubsystem extends BaseSubsystem {

    boolean listeningForLeft = false;
    boolean listeningForRight = false;

    XCANSparkMax activeLeft;
    XCANSparkMax activeRight;

    XCANSparkMax[] motors;

    DoubleProperty motorSpeed;

    @Inject
    public MotorControlSubsystem(XCANSparkMax.XCANSparkMaxFactory smFactory, PropertyFactory propertyFactory, ElectricalContract contract, DriveSubsystem ds) {
        propertyFactory.setPrefix(this);

        XCANSparkMax motor1 = smFactory.create(contract.getMotor1(), this.getPrefix(), "m1");
        XCANSparkMax motor2 = smFactory.create(contract.getMotor2(), this.getPrefix(), "m2");
        XCANSparkMax motor3 = smFactory.create(contract.getMotor3(), this.getPrefix(), "m3");
        XCANSparkMax motor4 = smFactory.create(contract.getMotor4(), this.getPrefix(), "m4");
        XCANSparkMax motor5 = smFactory.create(contract.getMotor5(), this.getPrefix(), "m5");

        motorSpeed = propertyFactory.createEphemeralProperty("Motor4Speed", 0.0);

        motors = new XCANSparkMax[]{motor1, motor2, motor3, motor4, motor5};
        updateMotor(motor1, motor2);
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
        motors[3].setReference(speed, CANSparkMax.ControlType.kVelocity);
    }
    public void setLeftPosition(double position) {
        motors[3].setReference(position, CANSparkMax.ControlType.kPosition);
    }

    @Override
    public void periodic() {
        for (XCANSparkMax motor : motors) {
            motor.periodic();
        }

        motorSpeed.set(motors[3].getVelocity());
    }
}
