package com.amhsrobotics.hatchpanel;

import com.amhsrobotics.autonomous.movement.commands.AutoSlider;
import com.amhsrobotics.hatchpanel.commands.ManualSlide;
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

public class Slider extends Subsystem {
	private static Slider ourInstance = new Slider();
	private WPI_TalonSRX slider = new WPI_TalonSRX(TalonIds.SLIDER);
	public static Slider getInstance() {
		return ourInstance;
	}

	private Slider() {
		super("Slider");
	}

	public void initHardware(){
		slider.configFactoryDefault();
		slider.setInverted(TalonInversions.SLIDER);
		slider.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
		slider.setSensorPhase(EncoderInversions.SLIDER);
		slider.configClosedLoopPeakOutput(0,.5);
//		slider.configClosedLoopPeakOutput(0,.5);
		slider.config_kP(0, PID.SLIDER[0]);
		slider.config_kI(0, PID.SLIDER[1]);
		slider.config_kD(0, PID.SLIDER[2]);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AutoSlider());
	}

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

	private TrapezoidalMotionProfile slide(final double position){
		System.out.println("Slide init pos: " + slider.getSelectedSensorPosition(0));
		return new TrapezoidalMotionProfile(MotionProfileValues.MAX_ACCELERATION, MotionProfileValues.MAX_VELOCITY,  slider.getSelectedSensorPosition(0)/ TicksPerInch.SLIDER, position, 0.06);
	}
	public void setEncoder(double value){
		slider.setSelectedSensorPosition(0);
	}
	public void manualSlide(final double value){
		new PushForward();
		if (Math.abs(value) > 0.2) {
			slider.set(ControlMode.PercentOutput, value);
		} else {
			slider.set(ControlMode.PercentOutput, 0);
		}
	}

	public void setSliderPosition(double position){
		new PushForward();
		slider.set(ControlMode.Position, position);
		//System.out.println("pos" + position);
	}

	public double getSliderPosition(){
		return slider.getSelectedSensorPosition();
	}

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