package frc.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.drive.constants.EncoderInversions;
import frc.robot.drive.constants.PID;
import frc.robot.drive.constants.TalonIds;
import frc.robot.drive.constants.TalonInversions;

public class DriveTrain extends Subsystem {
	private WPI_TalonSRX[] leftDrive = new WPI_TalonSRX[TalonIds.LEFT_DRIVE.length];
	private WPI_TalonSRX[] rightDrive = new WPI_TalonSRX[TalonIds.RIGHT_DRIVE.length];
	private static DriveTrain ourInstance = new DriveTrain();

	public static DriveTrain getInstance() {
		return ourInstance;
	}

	private DriveTrain() {
		for(int i = 0; i < leftDrive.length; i++){
			WPI_TalonSRX talonSRX = new WPI_TalonSRX(TalonIds.LEFT_DRIVE[i]);
			talonSRX.configFactoryDefault();
			talonSRX.setInverted(TalonInversions.LEFT_DRIVE[i]);
			if(i == 0){
				talonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
				talonSRX.setSensorPhase(EncoderInversions.LEFT_DRIVE);
				talonSRX.config_kP(0, PID.DRIVE[0]);
				talonSRX.config_kI(0, PID.DRIVE[1]);
				talonSRX.config_kD(0, PID.DRIVE[2]);
			} else {
				talonSRX.set(ControlMode.Follower, TalonIds.LEFT_DRIVE[0]);
			}
			leftDrive[i] = talonSRX;
		}
		for(int i = 0; i < rightDrive.length; i++){
			WPI_TalonSRX talonSRX = new WPI_TalonSRX(TalonIds.RIGHT_DRIVE[i]);
			talonSRX.configFactoryDefault();
			talonSRX.setInverted(TalonInversions.RIGHT_DRIVE[i]);
			if(i == 0){
				talonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
				talonSRX.setSensorPhase(EncoderInversions.RIGHT_DRIVE);
				talonSRX.config_kP(0, PID.DRIVE[0]);
				talonSRX.config_kI(0, PID.DRIVE[1]);
				talonSRX.config_kD(0, PID.DRIVE[2]);
			} else {
				talonSRX.set(ControlMode.Follower, TalonIds.RIGHT_DRIVE[0]);
			}
			rightDrive[i] = talonSRX;
		}
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void tankDrive(final double left, final double right){
		if(Math.abs(left) < 0.05){
			leftDrive[0].set(ControlMode.PercentOutput, 0);
		} else {
			leftDrive[0].set(ControlMode.PercentOutput, left);
		}
		if(Math.abs(right) < 0.05){
			rightDrive[0].set(ControlMode.PercentOutput, 0);
		} else {
			rightDrive[0].set(ControlMode.PercentOutput, right);
		}
	}

	public void wheelDrive(final double drive, final double turn, final boolean inPlace){
		final double turnE = 0.7;
		final double multiplier = 2.5;
		final double driveE = 0.5;
		double turnValue = turn * turnE * multiplier;
		double driveValue = drive * driveE;
		if(inPlace){
			if(Math.abs(turn) > 0.05){
				leftDrive[0].set(ControlMode.PercentOutput, -turnValue);
				rightDrive[0].set(ControlMode.PercentOutput, turnValue);
			} else {
				leftDrive[0].set(ControlMode.PercentOutput, 0);
				rightDrive[0].set(ControlMode.PercentOutput, 0);
			}
		} else {
			if(Math.abs(drive) > 0.05){
				if(Math.abs(turn) > 0.05){
					if(drive < - 0.05){
						leftDrive[0].set(ControlMode.PercentOutput, driveValue - turnValue);
						rightDrive[0].set(ControlMode.PercentOutput, driveValue + turnValue);
					} else {
						leftDrive[0].set(ControlMode.PercentOutput, driveValue + turnValue);
						rightDrive[0].set(ControlMode.PercentOutput, driveValue - turnValue);
					}
				} else {
					leftDrive[0].set(ControlMode.PercentOutput, driveValue);
					rightDrive[0].set(ControlMode.PercentOutput, driveValue);
				}
			} else {
				leftDrive[0].set(ControlMode.PercentOutput, 0);
				rightDrive[0].set(ControlMode.PercentOutput, 0);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
