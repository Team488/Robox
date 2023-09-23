package competition.operator_interface;

import javax.inject.Inject;
import javax.inject.Singleton;

import competition.subsystems.motor_control.commands.ChangingLeftMotor;
import competition.subsystems.motor_control.commands.ChangingRightMotor;
import competition.subsystems.motor_control.commands.SetM1;
import competition.subsystems.motor_control.commands.SetM2;
import competition.subsystems.motor_control.commands.SetM3;
import competition.subsystems.motor_control.commands.SetM4;
import competition.subsystems.motor_control.commands.SetM5;
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
            SetM1 setM1,
            SetM2 setM2,
            SetM3 setM3,
            SetM4 setM4,
            SetM5 setM5
    )
    {
        resetHeading.setHeadingToApply(90);
        operatorInterface.gamepad.getifAvailable(1).onTrue(resetHeading);

        // Listening for button to change motors
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.Back).onTrue(changingLeftMotor);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.Back).onFalse(changingLeftMotor);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.Start).onTrue(changingRightMotor);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.Start).onFalse(changingRightMotor);

        // Buttons to change motors
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.RightBumper).onTrue(setM1);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.Y).onTrue(setM2);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.X).onTrue(setM3);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.B).onTrue(setM4);
        operatorInterface.gamepad.getXboxButton(XXboxController.XboxButton.A).onTrue(setM5);
    }
}
