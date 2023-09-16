package competition.subsystems.motorControl;

import javax.inject.Inject;

import competition.electrical_contract.ElectricalContract;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANSparkMax;
import xbot.common.properties.IPropertySupport;
import xbot.common.properties.PropertyFactory;

public class MotorControlSubsystem extends BaseSubsystem {

    public final XCANSparkMax motor1;
    public final XCANSparkMax motor2;
    public final XCANSparkMax motor3;
    public final XCANSparkMax motor4;
    public final XCANSparkMax motor5;


    @Inject
    public MotorControlSubsystem(XCANSparkMax.XCANSparkMaxFactory smFactory, PropertyFactory propertyFactory, ElectricalContract contract) {

        propertyFactory.setPrefix(this);

        this.motor1 = smFactory.create(contract.getLeftLeader(), this.getPrefix(), "m1");
        this.motor2 = smFactory.create(contract.getLeftLeader(), this.getPrefix(), "m2");
        this.motor3 = smFactory.create(contract.getLeftLeader(), this.getPrefix(), "m3");
        this.motor4 = smFactory.create(contract.getLeftLeader(), this.getPrefix(), "m4");
        this.motor5 = smFactory.create(contract.getLeftLeader(), this.getPrefix(), "m5");
    }
}
