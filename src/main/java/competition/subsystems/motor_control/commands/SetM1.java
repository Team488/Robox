package competition.subsystems.motor_control.commands;

import competition.subsystems.motor_control.MotorControlSubsystem;
import xbot.common.command.BaseCommand;

import javax.inject.Inject;

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
