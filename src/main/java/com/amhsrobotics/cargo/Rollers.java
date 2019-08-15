package com.amhsrobotics.cargo;

import com.amhsrobotics.cargo.constants.IntakeSpeeds;
import com.amhsrobotics.cargo.constants.OuttakeSpeeds;
import com.amhsrobotics.cargo.constants.SwitchIds;
import com.amhsrobotics.cargo.constants.TalonIds;
import com.amhsrobotics.cargo.constants.TalonInversions;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem code for rollers
 */
public class Rollers extends Subsystem {
	private static Rollers ourInstance = new Rollers();
	private WPI_TalonSRX topRoller = new WPI_TalonSRX(TalonIds.ROLLERS[0]);
	private WPI_TalonSRX bottomRoller = new WPI_TalonSRX(TalonIds.ROLLERS[1]);
	private DigitalInput ballSensor = new DigitalInput(SwitchIds.BALL_SENSOR); //switch is inverted

	/**
	 * Static function to make this class a singleton
	 * @return Roller subystem
	 */
	public static Rollers getInstance() {
		return ourInstance;
	}

	/**
	 * Constructor for Roller subsystem
	 */
	private Rollers() {
		super("Rollers");
	}

	/**
	 * Initialize talons for the Roller subsystem
	 */
	public void initHardware(){
		topRoller.setInverted(TalonInversions.ROLLERS[0]);
		bottomRoller.setInverted(TalonInversions.ROLLERS[1]);
	}

	/**
	 * Sets no command to the default command for the Roller subsystem
	 */
	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * Sets the rollers to intake a cargo ball
	 * Checks if the ball is fully intaked based on the intake switch
	 */
	public void intake(){
		if(!ballDetected()){
			setRollerSpeeds(IntakeSpeeds.TOP_ROLLER, IntakeSpeeds.BOTTOM_ROLLER);
		} else {
			stopRollers();
		}
	}

	/**
	 * Sets rollers to outtake a cargo ball
	 */
	public void outtake(){
		setRollerSpeeds(OuttakeSpeeds.TOP_ROLLER, OuttakeSpeeds.BOTTOM_ROLLER);
	}

	/**
	 * Stops the rollers
	 */
	public void stopRollers(){
		setRollerSpeeds(0, 0);
	}

	/**
	 * Sets the roller speeds in percent output
	 * @param topRollerSpeed set top roller speeds
	 * @param bottomRollerSpeed set bottom rollers speeds
	 */
	private void setRollerSpeeds(final double topRollerSpeed, final double bottomRollerSpeed){
		topRoller.set(ControlMode.PercentOutput, topRollerSpeed);
		bottomRoller.set(ControlMode.PercentOutput, bottomRollerSpeed);
	}

	/**
	 * Returns if the ball is fully intaked
	 * @return if ball sensor is pressed
	 */
	public boolean ballDetected(){
		return !ballSensor.get(); //switch is inverted
	}
}