package competition.subsystems.motor_control.commands;

import competition.subsystems.motor_control.MotorControlSubsystem;
import xbot.common.command.BaseCommand;
import xbot.common.controls.sensors.XTimer;
import xbot.common.properties.BooleanProperty;
import xbot.common.properties.DoubleProperty;
import xbot.common.properties.PropertyFactory;

import javax.inject.Inject;

public class WaveFormDrive extends BaseCommand {

    DoubleProperty frequency;
    DoubleProperty ratio;
    DoubleProperty amplitude;
    DoubleProperty offset;
    DoubleProperty intakeOffset;
    DoubleProperty maxSpeed;
    DoubleProperty waveForm;

    BooleanProperty isClosedLoop;
    BooleanProperty isPosition;
    BooleanProperty isVelocity;

    MotorControlSubsystem motor;

    @Inject
    public WaveFormDrive(MotorControlSubsystem motor, PropertyFactory pf) {
        this.addRequirements(motor);
        pf.setPrefix(this);

        frequency = pf.createPersistentProperty("frequency", 1);
        ratio = pf.createPersistentProperty("ratio", 1);
        amplitude = pf.createPersistentProperty("amplitude", 0.1);
        offset = pf.createPersistentProperty("offset", 0);
        intakeOffset = pf.createPersistentProperty("intakeOffset", 0);
        maxSpeed = pf.createPersistentProperty("maxSpeed", 1);
        waveForm = pf.createPersistentProperty("waveForm", 0);
        isClosedLoop = pf.createPersistentProperty("isClosedLoop", false);
        isPosition = pf.createPersistentProperty("isPosition", false);
        isVelocity = pf.createPersistentProperty("isVelocity", false);

        this.motor = motor;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Math.abs(maxSpeed.get()) < 1) {return;}

        //System.out.println("WaveFormDrive executing.");

        /*
        if (!isClosedLoop.get()) {
            double power = offset.get() / maxSpeed.get() + ((amplitude.get() / maxSpeed.get())
                    * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));

            waveForm.set(power);
            motor.driveActive(power, 0);
        } else {
            double speed = offset.get() + (amplitude.get()
                    * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));

            waveForm.set(speed);
            motor.setLeftSpeed(speed);
         */
        double speed = 0;
        double position =0;

        if (isClosedLoop.get()) {
            System.out.println("closed loop");
            //closed loop
            if (isPosition.get()) {
                //closed loop; position
                position = offset.get() + (amplitude.get()
                        * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));

                waveForm.set(position);
                motor.setLeftPosition(position);
                motor.setRightPosition(position);
                motor.setIntakePosition(position);
            } else if(isVelocity.get()){
                //closed loop; velocity
                speed = offset.get() + (amplitude.get()
                        * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));

                waveForm.set(speed);
                System.out.println("Set speed running");
                motor.setRightSpeed(speed);
                motor.setLeftSpeed(speed*ratio.get());
                motor.setIntakeSpeed(speed);
            } else {
                //closed loop;
                speed = 0;
            }
        } else {
            System.out.println("open loop");
            //open loop
            if (isPosition.get()) {
                System.out.println("open position, not applicable");
                //open loop; position
                speed = 0;
            } else if (isVelocity.get()){
                //open loop; velocity
                double power = offset.get() / maxSpeed.get() + ((amplitude.get() / maxSpeed.get())
                        * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));

                waveForm.set(power);
                motor.driveActive(power, power);
//                motor.activeIntake.set(power);
            } else {
                speed = 0;}
        }
    }

//
//    @Override
//    public void execute() {
//        if (Math.abs(maxSpeed.get()) < 1) {return;}
//
//        //System.out.println("WaveFormDrive executing.");
//
//        /*
//        if (!isClosedLoop.get()) {
//            double power = offset.get() / maxSpeed.get() + ((amplitude.get() / maxSpeed.get())
//                    * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));
//
//            waveForm.set(power);
//            motor.driveActive(power, 0);
//        } else {
//            double speed = offset.get() + (amplitude.get()
//                    * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));
//
//            waveForm.set(speed);
//            motor.setLeftSpeed(speed);
//         */
//        double speed = 0;
//        double intakeSpeed = 0;
//        double position =0;
//        double intakePosition = 0;
//
//        if (isClosedLoop.get()) {
//            System.out.println("closed loop");
//            //closed loop
//            if (isPosition.get()) {
//                //closed loop; position
//                position = offset.get() + (amplitude.get()
//                        * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));
//                intakePosition = intakeOffset.get() + (amplitude.get()
//                        * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));
//
//                waveForm.set(position);
//                motor.setLeftPosition(position);
//                motor.setRightPosition(position);
//                motor.setIntakePosition(intakePosition);
//            } else if(isVelocity.get()){
//                //closed loop; velocity
//                speed = offset.get() + (amplitude.get()
//                        * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));
//                intakeSpeed = intakeOffset.get() + (amplitude.get()
//                        * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));
//
//                waveForm.set(speed);
//                System.out.println("Set speed running");
//                motor.setRightSpeed(speed);
//                motor.setLeftSpeed(speed*ratio.get());
//                motor.setIntakeSpeed(intakeSpeed);
//            } else {
//                //closed loop;
//                speed = 0;
//            }
//        } else {
//            System.out.println("open loop");
//            //open loop
//            if (isPosition.get()) {
//                System.out.println("open position, not applicable");
//                //open loop; position
//                speed = 0;
//            } else if (isVelocity.get()){
//                //open loop; velocity
//                double power = offset.get() / maxSpeed.get() + ((amplitude.get() / maxSpeed.get())
//                        * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));
//
//                double intakePower = intakeOffset.get() / maxSpeed.get() + ((amplitude.get() / maxSpeed.get())
//                        * Math.sin(2 * Math.PI * frequency.get() * XTimer.getFPGATimestamp()));
//
//                waveForm.set(power);
//                motor.driveActive(power, power, intakePower);
////                motor.activeIntake.set(power);
//            } else {
//                speed = 0;}
//        }
//    }


    /*public boolean isFinished() {
        System.out.println("isFinished");
        motor.driveActive(0, 0);
        return true;


    }*/
}
