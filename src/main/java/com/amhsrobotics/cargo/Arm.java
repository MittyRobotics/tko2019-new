package com.amhsrobotics.cargo;

import com.amhsrobotics.cargo.commands.ManualAngle;
import com.amhsrobotics.cargo.constants.ArmPosition;
import com.amhsrobotics.cargo.constants.ArmPositions;
import com.amhsrobotics.cargo.constants.EncoderInversions;
import com.amhsrobotics.cargo.constants.MotionProfileValues;
import com.amhsrobotics.cargo.constants.PID;
import com.amhsrobotics.cargo.constants.TalonIds;
import com.amhsrobotics.cargo.constants.TalonInversions;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import team1351.motionprofile.TrapezoidalMotionProfile;

/**
 * Subsystem code for Arm
 */
public class Arm extends Subsystem {
	private static Arm ourInstance = new Arm();
	private WPI_TalonSRX[] arm = new WPI_TalonSRX[TalonIds.ARM.length];

	/**
	 * Static function to make this class a singleton
	 * @return Arm subsystem
	 */
	public static Arm getInstance() {
		return ourInstance;
	}

	/**
	 * Constructor for Arm subsystem
	 */
	private Arm() {
		super("Arm");
	}


	/**
	 * Initialize talons for the Arm subsystem
	 */
	public void initHardware(){
		for (int i = 0; i < arm.length; i++) {
			WPI_TalonSRX talonSRX = new WPI_TalonSRX(TalonIds.ARM[i]);
			talonSRX.configFactoryDefault();
			talonSRX.setInverted(TalonInversions.ROLLERS[i]);
			if (i == 0) {
				talonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
				talonSRX.setSensorPhase(EncoderInversions.ARM);
				talonSRX.configClosedLoopPeakOutput(0,0.5);
				talonSRX.config_kP(0, PID.ARM[0]);
				talonSRX.config_kI(0, PID.ARM[1]);
				talonSRX.config_kD(0, PID.ARM[2]);
			} else {
				talonSRX.set(ControlMode.Follower, TalonIds.ARM[0]);
			}
			arm[i] = talonSRX;
		}
	}

	/**
	 * Sets default command to Manual Angle
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualAngle());
	}

	/**
	 * Gives position values for arm to move the armPosition
	 * @param armPosition the position of the arm that is being set
	 *                    Rocket sets the position to shoot into the bottom rocket
	 *                    Cargo sets the position to shoot into the cargo ship
	 *                    Ground sets the position to pickup from the ground
	 * @return a TrapezoidalMotionProfile set to go to armPosition
	 */
	public TrapezoidalMotionProfile angle(final ArmPosition armPosition){
		if(armPosition == ArmPosition.Rocket){
			return angle(ArmPositions.ROCKET);
		} else if(armPosition == ArmPosition.Cargo){
			return angle(ArmPositions.CARGO);
		} else {
			return angle(ArmPositions.GROUND);
		}
	}

	/**
	 * Gives position values for arm to move to position
	 * @param position the position of the arm that is being set
	 * @return a TrapezoidalMotionProfile set to go to position
	 */
	private TrapezoidalMotionProfile angle(double position){
		return new TrapezoidalMotionProfile(MotionProfileValues.MAX_ACCELERATION, MotionProfileValues.MAX_VELOCITY, arm[0].getSelectedSensorPosition(0), position, 0.06);
	}

	/**
	 * Sets arm to go position
	 * @param position the position the arm is set to go to
	 */
	public void setArmPosition(double position){
		arm[0].set(ControlMode.Position, position);
	}

	/**
	 * gives back the current arm position
	 * @return arm position
	 */
	public double getArmPosition(){
		return arm[0].getSelectedSensorPosition();
	}

	/**
	 * Sets the arm motor speeds based on percent output
	 * @param value the value to set the motor speeds to
	 */
	public void manualAngle(double value){
		if (Math.abs(value) > 0.2) {
			arm[0].set(ControlMode.PercentOutput, -value);
		} else {
			arm[0].set(ControlMode.PercentOutput, 0);
		}
	}

	/**
	 * Zeroes the encoder before match so we know our position
	 */
	public final void zeroEncoder() {
		arm[0].set(ControlMode.PercentOutput, -0.3);
		while (!arm[0].getSensorCollection().isRevLimitSwitchClosed() && DriverStation.getInstance().isTest()) {
//			System.out.println(conveyorTalons[0].getSelectedSensorPosition());
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		arm[0].set(ControlMode.PercentOutput, 0);
		//calibrateCommand.setFinished();
		System.out.println("Arm encoder reset!");
		arm[0].setSelectedSensorPosition(0);

		arm[0].set(ControlMode.PercentOutput, -0.3);
		while (!arm[0].getSensorCollection().isRevLimitSwitchClosed() && DriverStation.getInstance().isTest()) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		arm[0].set(ControlMode.PercentOutput, 0);
		System.out.println("Arm position reset: 1/2");
		arm[0].setSelectedSensorPosition(0);
		int timer = 0;
		while (timer < 25 && DriverStation.getInstance().isTest()) {
			timer ++;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		arm[0].set(ControlMode.PercentOutput, 0.2);
		timer = 0;
		while (timer < 30 && DriverStation.getInstance().isTest()) {
			timer ++;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		arm[0].set(ControlMode.PercentOutput, -0.15);
		while (!arm[0].getSensorCollection().isRevLimitSwitchClosed() && DriverStation.getInstance().isTest()) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Arm position reset: 2/2");
		arm[0].setSelectedSensorPosition(0);
		//arm[0].setSelectedSensorPosition(0);
	}
}