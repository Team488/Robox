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

    @Override
    public DeviceInfo getLeftLeader() {
        return new DeviceInfo(0, true);
    }

    @Override
    public DeviceInfo getRightLeader() {
        return new DeviceInfo(0, true);
    }

    /*
    public DeviceInfo getMotor1() {return new DeviceInfo(channel: number, inverted: true)

    And then just like 5 of them?
     */
    @Override
    public DeviceInfo getMotor1() {return new DeviceInfo(1, true);}
    @Override
    public DeviceInfo getMotor2() {return new DeviceInfo(2, true);}
    @Override
    public DeviceInfo getMotor3() {return new DeviceInfo(3, true);}
    @Override
    public DeviceInfo getMotor4() {return new DeviceInfo(4, true);}
    @Override
    public DeviceInfo getMotor5() {return new DeviceInfo(5, true);}

}
