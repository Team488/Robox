package competition.operator_interface;

import javax.inject.Inject;
import javax.inject.Singleton;

import competition.subsystems.CollectorSubsystem;
import competition.subsystems.motor_control.commands.SetMotor;
import competition.subsystems.motor_control.commands.ChangingLeftMotor;
import competition.subsystems.motor_control.commands.ChangingRightMotor;
import competition.subsystems.motor_control.commands.WaveFormDrive;
import xbot.common.controls.sensors.XXboxController;
import xbot.common.subsystems.pose.commands.SetRobotHeadingCommand;

/**
 * Maps operator interface buttons to commands
 */
@Singleton
public class OperatorCommandMap {

    @Inject
    public OperatorCommandMap() {}
    
    // Example for setting up a command to fire when a button is pressed:
    @Inject
    public void setupMyCommands(
            OperatorInterface operatorInterface,
            SetRobotHeadingCommand resetHeading,
            ChangingLeftMotor changingLeftMotor,
            ChangingRightMotor changingRightMotor,
            WaveFormDrive waveFormDrive,
            SetMotor setMotor0,
            SetMotor setMotor1,
            SetMotor setMotor2,
            SetMotor setMotor3,
            SetMotor setMotor4,
            CollectorSubsystem collector
    )
    {
        resetHeading.setHeadingToApply(90);
        //operatorInterface.gamepad.getifAvailable(1).onTrue(resetHeading);

        // Listening for button to change motors
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.Back).onTrue(changingLeftMotor);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.Back).onFalse(changingLeftMotor);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.Start).onTrue(changingRightMotor);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.Start).onFalse(changingRightMotor);

        // Initialize setMotors
        setMotor0.setMotor(0);
        setMotor1.setMotor(1);
        setMotor2.setMotor(2);
        setMotor3.setMotor(3);
        setMotor4.setMotor(4);

        // Buttons to change motors
       operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.RightBumper).onTrue(setMotor0);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.Y).onTrue(setMotor1);
       operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.X).onTrue(setMotor2);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.B).onTrue(setMotor3);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.A).onTrue(setMotor4);

        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.X).whileTrue(waveFormDrive);

        operatorInterface.gamepad.getXboxButton((XXboxController.XboxButton.RightBumper)).whileTrue(collector.getForwardCommand());
        operatorInterface.gamepad.getXboxButton((XXboxController.XboxButton.LeftBumper)).whileTrue(collector.getReverseCommand());

    }
}
