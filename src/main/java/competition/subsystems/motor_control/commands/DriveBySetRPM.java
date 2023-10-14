package competition.subsystems.motor_control.commands;

import competition.subsystems.motor_control.MotorControlSubsystem;
import xbot.common.command.BaseCommand;
import xbot.common.properties.DoubleProperty;
import xbot.common.properties.PropertyFactory;

import javax.inject.Inject;

public class DriveBySetRPM extends BaseCommand {


    MotorControlSubsystem motor;
    @Inject
    public DriveBySetRPM(MotorControlSubsystem motor, PropertyFactory pf) {
        this.addRequirements(motor);
        pf.setPrefix(this);



        this.motor = motor;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {

    }
}
