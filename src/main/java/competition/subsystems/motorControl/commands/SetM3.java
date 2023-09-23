package competition.subsystems.motorControl.commands;

import competition.subsystems.motorControl.MotorControlSubsystem;
import xbot.common.command.BaseCommand;

import javax.inject.Inject;

public class SetM3 extends BaseCommand {
    MotorControlSubsystem motor;

    @Inject
    public SetM3(MotorControlSubsystem motor) {
        this.motor = motor;
    }

    @Override
    public void initialize() {
        motor.setMotor(2);
    }

    @Override
    public void execute() {

    }

    public boolean isFinished() {
        return true;
    }
}
