package competition.electrical_contract;

import xbot.common.injection.electrical_contract.CANTalonInfo;
import xbot.common.injection.electrical_contract.DeviceInfo;
import xbot.common.injection.electrical_contract.DeviceInfo;

public abstract class ElectricalContract {

    public abstract DeviceInfo getMotor1();
    public abstract DeviceInfo getMotor2();
    public abstract DeviceInfo getMotor3();
    public abstract DeviceInfo getMotor4();
    public abstract DeviceInfo getMotor5();
}
