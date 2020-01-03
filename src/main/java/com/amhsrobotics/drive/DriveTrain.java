package com.amhsrobotics.drive;

import com.amhsrobotics.drive.commands.TankDrive;
import com.amhsrobotics.drive.constants.EncoderInversions;
import com.amhsrobotics.drive.constants.PID;
import com.amhsrobotics.drive.constants.TalonIds;
import com.amhsrobotics.drive.constants.TalonInversions;
import com.amhsrobotics.drive.constants.TicksPerInch;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem code for drivetrain
 */
public class DriveTrain extends Subsystem {
	private WPI_TalonSRX[] leftDrive = new WPI_TalonSRX[TalonIds.LEFT_DRIVE.length];
	private WPI_TalonSRX[] rightDrive = new WPI_TalonSRX[TalonIds.RIGHT_DRIVE.length];
	private static DriveTrain ourInstance = new DriveTrain();

	/**
	 * Static function to make this class a singleton
	 * @return DriveTrain subsystem
	 */
	public static DriveTrain getInstance() {
		return ourInstance;
	}

	/**
	 * Constructor for Drivetrain subsystem
	 */
	private DriveTrain() {
		super("DriveTrain");
	}

	/**
	 * Configure talons for the subsystem
	 */
	public void initHardware() {
		for (int i = 0; i < leftDrive.length; i++) {
			WPI_TalonSRX talonSRX = new WPI_TalonSRX(TalonIds.LEFT_DRIVE[i]);
			talonSRX.configFactoryDefault();
			talonSRX.setInverted(TalonInversions.LEFT_DRIVE[i]);
			talonSRX.setNeutralMode(NeutralMode.Coast);
			if (i == 0) {
				talonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
				talonSRX.setSensorPhase(EncoderInversions.LEFT_DRIVE);
				talonSRX.config_kP(0, PID.DRIVE[0]);
				talonSRX.config_kI(0, PID.DRIVE[1]);
				talonSRX.config_kD(0, PID.DRIVE[2]);
				talonSRX.setSelectedSensorPosition(0);
			}
			leftDrive[i] = talonSRX;
		}
		for (int i = 0; i < rightDrive.length; i++) {
			WPI_TalonSRX talonSRX = new WPI_TalonSRX(TalonIds.RIGHT_DRIVE[i]);
			talonSRX.configFactoryDefault();
			talonSRX.setInverted(TalonInversions.RIGHT_DRIVE[i]);
			talonSRX.setNeutralMode(NeutralMode.Coast);
			if (i == 0) {
				talonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
				talonSRX.setSensorPhase(EncoderInversions.RIGHT_DRIVE);
				talonSRX.config_kP(0, PID.DRIVE[0]);
				talonSRX.config_kI(0, PID.DRIVE[1]);
				talonSRX.config_kD(0, PID.DRIVE[2]);
				talonSRX.setSelectedSensorPosition(0);
			}
			rightDrive[i] = talonSRX;
		}
	}

	/**
	 * Sets the default command to Tank Drive
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}

	/**
	 * Tank Drive code that moves the left side based on a parameter and the right side based on a parameter
	 * Uses percent output
	 * @param left the input value for the left side
	 * @param right the input value for the right side
	 */
	public void tankDrive(double left, double right) {
		tankDrive(left, right, 1);
	}

	/**
	 * Tank Drive code that moves the left side based on a parameter and the right side based on a parameter, and scalar for the speeds
	 * Uses percent output
	 * @param left the input value for the left side
	 * @param right the input value for the right side
	 * @param percentCap the speed multiplier for both sides between 0-1
	 */
	public void tankDrive(double left, double right, double percentCap) {
		percentCap = Math.max(0, Math.min(percentCap, 1));
		left *= percentCap;
		right *= percentCap;

		if (Math.abs(left) < 0.1) {
			leftDrive[0].set(ControlMode.PercentOutput, 0);
			leftDrive[1].set(ControlMode.PercentOutput, 0);
		} else {
			leftDrive[0].set(ControlMode.PercentOutput, left);
			leftDrive[1].set(ControlMode.PercentOutput, left);
		}
		if (Math.abs(right) < 0.1) {
			rightDrive[0].set(ControlMode.PercentOutput, 0);
			rightDrive[1].set(ControlMode.PercentOutput, 0);
		} else {
			rightDrive[0].set(ControlMode.PercentOutput, right);
			rightDrive[1].set(ControlMode.PercentOutput, right);
		}
	}

	/**
	 * Tank Drive code that moves the left side based on a parameter and the right side based on a parameter
	 * Uses velocity PID
	 * @param left the input value for the left side
	 * @param right the input value for the right side
	 */
	public void tankVelocity(double left, double right) {
		left *= TicksPerInch.DRIVE_HIGH/10;
		right *= TicksPerInch.DRIVE_HIGH/10;

		leftDrive[0].set(ControlMode.Velocity, left);
		leftDrive[1].set(ControlMode.PercentOutput, leftDrive[0].getMotorOutputPercent());
		rightDrive[0].set(ControlMode.Velocity, right);
		leftDrive[1].set(ControlMode.PercentOutput, rightDrive[0].getMotorOutputPercent());
	}

	/**
	 * Moves the left and right wheels to a new position in inches
	 * @param leftDistance how much to move the left wheel
	 * @param rightDistance how much to move the right wheel
	 * @param maxOutput maximum speed robot can obtain on each side (0-1)
	 */
	public void translation(final double leftDistance, final double rightDistance, double maxOutput) {
		leftDrive[0].configClosedLoopPeakOutput(0, maxOutput);
		rightDrive[0].configClosedLoopPeakOutput(0, maxOutput);
		leftDrive[0].set(ControlMode.Position, leftDistance * TicksPerInch.DRIVE_HIGH);
		rightDrive[0].set(ControlMode.Position, rightDistance * TicksPerInch.DRIVE_HIGH);
	}

	/**
	 * Moves the left and right wheels to a new position in inches
	 * @param leftDistance how much to move the left wheel
	 * @param rightDistance how much to move the right wheel
	 */
	public void translation(final double leftDistance, final double rightDistance) {
		translation(leftDistance, rightDistance, 1);
	}

	/**
	 * Returns current error of translation
	 * @return movement error
	 */
	public double getTranslationError() {
		return leftDrive[0].getClosedLoopError();
	}

	/**
	 * Left encoder position
	 * @return left encoder position
	 */
	public double getLeftEncoder() {
		return leftDrive[0].getSelectedSensorPosition();
	}

	/**
	 * Right encoder position
	 * @return right encoder position
	 */
	public double getRightEncoder() {
		return rightDrive[0].getSelectedSensorPosition();
	}

	/**
	 * Left encoder velocity
	 * @return left encoder velocity
	 */
	public double getLeftVelocity() {
		return leftDrive[0].getSelectedSensorVelocity();
	}

	/**
	 * Right encoder velocity
	 * @return Right encoder velocity
	 */
	public double getRightVelocity() {
		return rightDrive[0].getSelectedSensorVelocity();
	}

	/**
	 * Rotates the robot to a certain angle
	 * @param angle the angle the robot turns
	 * @param maxSpeed maximum speed robot can obtain while turning (0-1)
	 */
	public void rotation(final double angle, final double maxSpeed) {
//		double target;
//		controller.setContinuous(true);
//		controller.setInputRange(0, 360);
//		controller.setOutputRange(-maxSpeed, maxSpeed);
//		if (Gyro.getInstance().getAngle() + angle < 0) {
//			target = Gyro.getInstance().getAngle() + angle + 360;
//		} else if (Gyro.getInstance().getAngle() + angle >= 360) {
//			target = Gyro.getInstance().getAngle() + angle - 360;
//		} else {
//			target = Gyro.getInstance().getAngle() + angle;
//		}
//		controller.setSetpoint(target);
//		controller.enable();
	}

	/**
	 * Returns turning error
	 * @return turning error
	 */
	public double getRotationError() {
//		return controller.getError();
		return 0;
	}
}