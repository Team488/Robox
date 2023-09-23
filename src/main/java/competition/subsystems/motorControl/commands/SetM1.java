package competition.subsystems.motorControl.commands;

import competition.subsystems.motorControl.MotorControlSubsystem;
import xbot.common.command.BaseCommand;

import javax.inject.Inject;
import javax.swing.*;

public class SetM1 extends BaseCommand {

    MotorControlSubsystem motor;

    @Inject
    public SetM1(MotorControlSubsystem motor) {
        this.motor = motor;
    }

    @Override
    public void initialize() {
        motor.setMotor(0);
    }

    @Override
    public void execute() {

    }

    public boolean isFinished() {
        return true;
    }
}
