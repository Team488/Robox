package competition.subsystems.motorControl.commands;

import competition.operator_interface.OperatorInterface;
import competition.subsystems.motorControl.MotorControlSubsystem;
import xbot.common.command.BaseCommand;
import xbot.common.controls.sensors.XXboxController;

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
