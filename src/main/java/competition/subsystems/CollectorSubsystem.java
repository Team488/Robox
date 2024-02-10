package competition.subsystems;

import xbot.common.controls.actuators.XCANSparkMax;
import xbot.common.injection.electrical_contract.DeviceInfo;
import xbot.common.properties.PropertyFactory;
import xbot.common.subsystems.simple.SimpleMotorSubsystem;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CollectorSubsystem extends SimpleMotorSubsystem {

    XCANSparkMax motor;

    @Inject
    public CollectorSubsystem(PropertyFactory pf, XCANSparkMax.XCANSparkMaxFactory smFactory) {
        super("Collector", pf, 1.0, -1.0);
        motor = smFactory.create(new DeviceInfo("collectorMotor", 33, true), this.getPrefix(), "collector motor");
    }

    @Override
    public void setPower(double power) {
        motor.set(-power);
    }
}
