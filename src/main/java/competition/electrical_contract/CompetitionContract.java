package competition.electrical_contract;

import javax.inject.Inject;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import competition.subsystems.pose.PoseSubsystem;
import xbot.common.injection.electrical_contract.CANTalonInfo;
import xbot.common.injection.electrical_contract.DeviceInfo;

public class CompetitionContract extends ElectricalContract {

    protected final double simulationScalingValue = 256.0 * PoseSubsystem.INCHES_IN_A_METER;

    @Inject
    public CompetitionContract() {}

    /*
    public DeviceInfo getMotorX() {return new DeviceInfo(channel: number, inverted: true)

    And then just like 5 of them?
     */
    @Override
    public DeviceInfo getMotor1() {return new DeviceInfo(32, true);}
    @Override
    public DeviceInfo getMotor2() {return new DeviceInfo(33, true);}
    @Override
    public DeviceInfo getMotor3() {return new DeviceInfo(34, true);}
    @Override
    public DeviceInfo getMotor4() {return new DeviceInfo(35, true);}
    @Override
    public DeviceInfo getMotor5() {return new DeviceInfo(36, true);}

}
