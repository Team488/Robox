package competition.subsystems.motorControl.commands;

import competition.subsystems.motorControl.MotorControlSubsystem;
import xbot.common.command.BaseCommand;

import javax.inject.Inject;

public class ChangingLeftMotor extends BaseCommand {
    MotorControlSubsystem motor;

    @Inject
    public ChangingLeftMotor(MotorControlSubsystem motor) {
        this.motor = motor;
    }

    @Override
    public void initialize() {
        motor.listenForLeft();
    }

    @Override
    public void execute() {

    }

    public boolean isFinished() {
        return true;
    }
}
