package competition.subsystems.motorControl.commands;

import competition.subsystems.motorControl.MotorControlSubsystem;
import xbot.common.command.BaseCommand;

import javax.inject.Inject;

public class SetM5 extends BaseCommand {
    MotorControlSubsystem motor;

    @Inject
    public SetM5(MotorControlSubsystem motor) {
        this.motor = motor;
    }

    @Override
    public void initialize() {
        motor.setMotor(4);
    }

    @Override
    public void execute() {

    }

    public boolean isFinished() {
        return true;
    }
}
