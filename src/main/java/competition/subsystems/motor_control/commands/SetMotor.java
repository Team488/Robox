package competition.subsystems.motor_control.commands;

import competition.subsystems.motor_control.MotorControlSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import xbot.common.command.BaseCommand;

import javax.inject.Inject;

public class SetMotor extends BaseCommand {

    MotorControlSubsystem motor;
    int index;

    @Inject
    public SetMotor(MotorControlSubsystem motor) {
        this.motor = motor;
    }

    public void setMotor(int index) {
        this.index = index;
        //System.out.println("Set index: "+index);
    }

    @Override
    public void initialize() {
//        motor.setMotor(index);
    }

    @Override
    public void execute() {

    }

    public boolean isFinished() {
        return true;
    }
}
