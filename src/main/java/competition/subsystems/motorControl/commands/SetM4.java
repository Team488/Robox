package competition.subsystems.motorControl.commands;

import competition.subsystems.motorControl.MotorControlSubsystem;
import xbot.common.command.BaseCommand;

import javax.inject.Inject;

public class SetM4 extends BaseCommand {
    MotorControlSubsystem motor;

    @Inject
    public SetM4(MotorControlSubsystem motor) {
        this.motor = motor;
    }

    @Override
    public void initialize() {
        motor.setMotor(3);
    }

    @Override
    public void execute() {

    }

    public boolean isFinished() {
        return true;
    }
}
