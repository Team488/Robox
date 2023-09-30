package competition.subsystems.drive;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

import competition.electrical_contract.ElectricalContract;
import xbot.common.controls.actuators.XCANSparkMax;
import xbot.common.math.PIDManager;
import xbot.common.math.XYPair;
import xbot.common.math.PIDManager.PIDManagerFactory;
import xbot.common.properties.DoubleProperty;
import xbot.common.properties.PropertyFactory;
import xbot.common.subsystems.drive.BaseDriveSubsystem;

@Singleton
public class DriveSubsystem extends BaseDriveSubsystem {
    private static Logger log = Logger.getLogger(DriveSubsystem.class);
    
    ElectricalContract contract;
    
    public XCANSparkMax leftMotor;
    public XCANSparkMax rightMotor;

    private final PIDManager positionPid;
    private final PIDManager rotationPid;

    private double scalingFactorFromTicksToInches = 1.0 / 256.0;

    DoubleProperty leftPowerProperty;
    DoubleProperty rightPowerProperty;

    @Inject
    public DriveSubsystem(XCANSparkMax.XCANSparkMaxFactory talonFactory, PropertyFactory propertyFactory, ElectricalContract contract, PIDManagerFactory pf) {
        log.info("Creating DriveSubsystem");

        propertyFactory.setPrefix(this);
        leftPowerProperty = propertyFactory.createEphemeralProperty("LeftPower", 0);
        rightPowerProperty = propertyFactory.createEphemeralProperty("RightPower", 0);

        positionPid = pf.create(getPrefix() + "PositionPID");
        rotationPid = pf.create(getPrefix() + "RotationPID");
    }

    public void tankDrive(double leftJoystickPower, double rightJoystickPower) {
        /*this.leftMotor.set(leftJoystickPower);
        this.rightMotor.set(rightJoystickPower);

        this.leftPowerProperty.set(leftJoystickPower);
        this.rightPowerProperty.set(rightJoystickPower);*/
    }

    @Override
    public PIDManager getPositionalPid() {
        return positionPid;
    }

    @Override
    public PIDManager getRotateToHeadingPid() {
        return rotationPid;
    }

    @Override
    public PIDManager getRotateDecayPid() {
        return null;
    }

    @Override
    public void move(XYPair translate, double rotate) {
        double y = translate.y;

        double left = y - rotate;
        double right = y + rotate;

        this.leftMotor.set(left);
        this.rightMotor.set(right);
    }

    @Override
    public double getLeftTotalDistance() {
        return 0;
    }

    @Override
    public double getRightTotalDistance() {
        return 0;
    }

    @Override
    public double getTransverseDistance() {
        return 0;
    }

//    @Override
//    public void periodic() {
//        leftMotor.periodic();
//        rightMotor.periodic();
//    }
}
