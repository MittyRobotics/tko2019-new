package frc.robot.cargo;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.cargo.commands.ManualAngle;
import frc.robot.cargo.constants.ArmPosition;
import frc.robot.cargo.constants.ArmPositions;
import frc.robot.cargo.constants.EncoderInversions;
import frc.robot.cargo.constants.MotionProfileValues;
import frc.robot.cargo.constants.TalonIds;
import frc.robot.cargo.constants.TalonInversions;
import frc.robot.cargo.constants.PID;
import motion_profile.TrapezoidalMotionProfile;

public class Arm extends Subsystem {
	private static Arm ourInstance = new Arm();
	private WPI_TalonSRX[] arm = new WPI_TalonSRX[TalonIds.ARM.length];
	public static Arm getInstance() {
		return ourInstance;
	}

	private Arm() {

	}

	public void initHardware(){
		for (int i = 0; i < arm.length; i++) {
			WPI_TalonSRX talonSRX = new WPI_TalonSRX(TalonIds.ARM[i]);
			talonSRX.configFactoryDefault();
			talonSRX.setInverted(TalonInversions.ROLLERS[i]);
			if (i == 0) {
				talonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
				talonSRX.setSensorPhase(EncoderInversions.ARM);
				talonSRX.config_kP(0, PID.ARM[0]);
				talonSRX.config_kI(0, PID.ARM[1]);
				talonSRX.config_kD(0, PID.ARM[2]);
			} else {
				talonSRX.set(ControlMode.Follower, TalonIds.ARM[0]);
			}
			arm[i] = talonSRX;
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualAngle());
	}
	public TrapezoidalMotionProfile angle(final ArmPosition armPosition){
		if(armPosition == ArmPosition.Rocket){
			return angle(ArmPositions.ROCKET);
		} else if(armPosition == ArmPosition.Cargo){
			return angle(ArmPositions.CARGO);
		} else {
			return angle(ArmPositions.GROUND);
		}
	}
	private TrapezoidalMotionProfile angle(double position){
		return new TrapezoidalMotionProfile(MotionProfileValues.MAX_ACCELERATION, MotionProfileValues.MAX_VELOCITY, Math.abs(position - arm[0].getSelectedSensorPosition()), 0.02, position - arm[0].getSelectedSensorPosition() < 0);
	}

	public void setArmPosition(double position){
		arm[0].set(ControlMode.Position, position);
	}
	public double getArmPosition(){
		return arm[0].getSelectedSensorPosition();
	}
	public void manualAngle(double value){
		if (Math.abs(value) > 0.1) {
			arm[0].set(ControlMode.PercentOutput, value);
		} else {
			arm[0].set(ControlMode.PercentOutput, 0);
		}
	}
}