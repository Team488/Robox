package competition.subsystems.motor_control.commands;

import competition.operator_interface.OperatorInterface;
import competition.subsystems.motor_control.MotorControlSubsystem;
import xbot.common.command.BaseCommand;
import xbot.common.math.MathUtils;

import javax.inject.Inject;

public class SpinMotorWithJoysticks extends BaseCommand {

    OperatorInterface oi;
    MotorControlSubsystem motor;

    @Inject
    public SpinMotorWithJoysticks(MotorControlSubsystem motor, OperatorInterface oi) {
        this.oi = oi;
        this.motor = motor;
        this.addRequirements(motor);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        motor.driveActive(
                MathUtils.deadband(oi.gamepad.getLeftVector().y, 0.15),
                MathUtils.deadband(oi.gamepad.getRightVector().y, 0.15)
        );
    }

}
