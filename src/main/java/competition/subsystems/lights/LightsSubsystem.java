package competition.subsystems.lights;

import edu.wpi.first.wpilibj.SerialPort;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.sensors.XTimer;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class LightsSubsystem extends BaseSubsystem {
    SerialPort a;
    double time = XTimer.getFPGATimestamp();
    boolean on = true;

    @Inject
    public LightsSubsystem() {
        a = new SerialPort(9600, SerialPort.Port.kUSB1, 8);
    }

    @Override
    public void periodic() {
        System.out.println("??????????????????????");
        if (XTimer.getFPGATimestamp() - time >= 1) {
            time = XTimer.getFPGATimestamp();
            on = !on;
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(a.writeString("1"));
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            //a.flush();
        }
    }
}
