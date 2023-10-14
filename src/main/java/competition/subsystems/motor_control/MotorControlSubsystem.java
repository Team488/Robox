package competition.subsystems.motor_control;

import javax.inject.Inject;
import javax.inject.Singleton;

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
        // leftPower and rightPower ranges from 1 to -1.
        /* Test code in electrical.
        double setPoint = 2200;
        double motor_cmd = setPoint * 0.00017123; // -1 to 1
        double diff = setPoint - activeLeft.getVelocity(); // Now vs desired
        double kP = 0.0002; // How much we want to adjust how much the feedback changes our system

        double final_cmd = motor_cmd + (diff * kP);
        double diffKp = diff * kP;

        activeLeft.set(final_cmd);
        activeRight.set(rightPower);
        System.out.println("pos: " + activeLeft.getPosition());
        System.out.println(activeLeft.get());
        System.out.println("diffKp: " + diffKp); */
        activeLeft.set(leftPower);
        activeRight.set(rightPower);
    }

    public void setLeftSpeed() {

    }

    @Override
    public void periodic() {
        for (XCANSparkMax motor : motors) {
            motor.periodic();
        }

        motorSpeed.set(motors[3].getVelocity());
    }
}
