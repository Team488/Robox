package competition.subsystems.motor_control.commands;

import competition.subsystems.motor_control.MotorControlSubsystem;
import xbot.common.command.BaseCommand;

import javax.inject.Inject;

public class ChangingRightMotor extends BaseCommand {
    MotorControlSubsystem motor;

    @Inject
    public ChangingRightMotor(MotorControlSubsystem motor) {
        this.motor = motor;
    }

    @Override
    public void initialize() {
        motor.listenForRight();
    }

    @Override
    public void execute() {

    }

    public boolean isFinished() {
        return true;
    }
}
