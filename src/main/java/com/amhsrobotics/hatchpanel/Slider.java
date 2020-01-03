package com.amhsrobotics.hatchpanel;

import com.amhsrobotics.autonomous.movement.commands.AutoSlider;
import com.amhsrobotics.hatchpanel.commands.PushForward;
import com.amhsrobotics.hatchpanel.constants.MotionProfileValues;
import com.amhsrobotics.hatchpanel.constants.SliderPosition;
import com.amhsrobotics.hatchpanel.constants.SliderPositions;
import com.amhsrobotics.hatchpanel.constants.TalonIds;
import com.amhsrobotics.hatchpanel.constants.TicksPerInch;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.amhsrobotics.hatchpanel.constants.EncoderInversions;
import com.amhsrobotics.hatchpanel.constants.PID;
import com.amhsrobotics.hatchpanel.constants.TalonInversions;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;

/**
 * Subsystem code for Slider
 */
public class Slider extends Subsystem {
	private static Slider ourInstance = new Slider();
	private WPI_TalonSRX slider = new WPI_TalonSRX(TalonIds.SLIDER);

	/**
	 * Static function to make this class a singleton
	 * @return Slider subsystem
	 */
	public static Slider getInstance() {
		return ourInstance;
	}

	/**
	 * Constructor for Slider Subsystem
	 */
	private Slider() {
		super("Slider");
	}

	/**
	 * Configure talon for the Slider subsystem
	 */
	public void initHardware(){
		slider.configFactoryDefault();
		slider.setInverted(TalonInversions.SLIDER);
		slider.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
		slider.setSensorPhase(EncoderInversions.SLIDER);
		slider.configClosedLoopPeakOutput(0,.5);
		slider.config_kP(0, PID.SLIDER[0]);
		slider.config_kI(0, PID.SLIDER[1]);
		slider.config_kD(0, PID.SLIDER[2]);
	}

	/**
	 * Sets default command to Auto Slider
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AutoSlider());
	}

	/**
	 * Gives position values for slider to move the sliderPosition
	 * @param sliderPosition the position of the slider that is being set
	 *                    Left sets the position for left rocket ship
	 *                    Middle sets the position for cargo ship
	 *                    Right sets the position for right rocket ship
	 * @return a TrapezoidalMotionProfile set to go to sliderPosition
	 */
	public TrapezoidalMotionProfile slide(final SliderPosition sliderPosition){
		new PushForward();
		if(sliderPosition == SliderPosition.Left){
			return slide(SliderPositions.LEFT);
		} else if(sliderPosition == SliderPosition.Middle){
			return slide(SliderPositions.MIDDLE);
		} else {
			return slide(SliderPositions.RIGHT);
		}
	}

	/**
	 * Gives position values for slider to move to position
	 * @param position the position of the slider that is being set
	 * @return a TrapezoidalMotionProfile set to go to position
	 */
	private TrapezoidalMotionProfile slide(final double position){
		System.out.println("Slide init pos: " + slider.getSelectedSensorPosition(0));
		return new TrapezoidalMotionProfile(MotionProfileValues.MAX_ACCELERATION, MotionProfileValues.MAX_VELOCITY,  slider.getSelectedSensorPosition(0)/ TicksPerInch.SLIDER, position, 0.06);
	}

	/**
	 * Sets the slider motor speed based on percent output
	 * @param value the value to set the motor speed to
	 */
	public void manualSlide(final double value){
		new PushForward();
		if (Math.abs(value) > 0.2) {
			slider.set(ControlMode.PercentOutput, value);
		} else {
			slider.set(ControlMode.PercentOutput, 0);
		}
	}

	/**
	 * Sets the current encoder value to a new value
	 * @param pos the new value of the encoder at the current position
	 */
	public void newSliderPos(int pos){
		slider.setSelectedSensorPosition(pos);
	}

	/**
	 * Moves the slider to position
	 * Subsystem is pushed forward first
	 * @param position the value the slider is set to go to
	 */
	public void setSliderPosition(double position){
		new PushForward();
		slider.set(ControlMode.Position, position);
	}

	/**
	 * Zeroes the encoder before match
	 */
	public final void zeroEncoder() {
		new PushForward();
		slider.set(ControlMode.PercentOutput, 0.2);
		while (!slider.getSensorCollection().isFwdLimitSwitchClosed() && DriverStation.getInstance().isTest()) {
			try {
				System.out.println(slider.getSensorCollection().isFwdLimitSwitchClosed());
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		slider.set(ControlMode.PercentOutput, 0);
		System.out.println("Slider position reset: 1/2");
		slider.setSelectedSensorPosition(0);
		int timer = 0;
		while (timer < 25 && DriverStation.getInstance().isTest()) {
			timer ++;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		slider.set(ControlMode.PercentOutput, -0.2);
		timer = 0;
		while (timer < 20 && DriverStation.getInstance().isTest()) {
			timer ++;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		slider.set(ControlMode.PercentOutput, 0.1);
		while (!slider.getSensorCollection().isFwdLimitSwitchClosed() && DriverStation.getInstance().isTest()) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Slider position reset: 2/2");
		slider.setSelectedSensorPosition(0);

	}
}