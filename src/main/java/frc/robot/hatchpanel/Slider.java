package frc.robot.hatchpanel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.hatchpanel.commands.ManualSlide;
import frc.robot.hatchpanel.constants.EncoderInversions;
import frc.robot.hatchpanel.constants.MotionProfileValues;
import frc.robot.hatchpanel.constants.PID;
import frc.robot.hatchpanel.constants.SliderPosition;
import frc.robot.hatchpanel.constants.SliderPositions;
import frc.robot.hatchpanel.constants.TalonIds;
import frc.robot.hatchpanel.constants.TalonInversions;
import frc.robot.hatchpanel.constants.TicksPerInch;
import team1351.motionprofile.TrapezoidalMotionProfile;

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
		slider.config_kP(0, PID.SLIDER[0]);
		slider.config_kI(0, PID.SLIDER[1]);
		slider.config_kD(0, PID.SLIDER[2]);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualSlide());
	}
	public TrapezoidalMotionProfile slide(final SliderPosition sliderPosition){
		if(sliderPosition == SliderPosition.Left){
			return slide(SliderPositions.LEFT);
		} else if(sliderPosition == SliderPosition.Middle){
			return slide(SliderPositions.MIDDLE);
		} else {
			return slide(SliderPositions.RIGHT);
		}
	}
	private TrapezoidalMotionProfile slide(final double position){

		return new TrapezoidalMotionProfile(MotionProfileValues.MAX_ACCELERATION, MotionProfileValues.MAX_VELOCITY, (Math.abs(position * TicksPerInch.SLIDER - slider.getSelectedSensorPosition()))/TicksPerInch.SLIDER, 0.02, position - TicksPerInch.SLIDER * slider.getSelectedSensorPosition() < 0);
	}

	public void manualSlide(final double value){
		if (Math.abs(value) > 0.2) {
			slider.set(ControlMode.PercentOutput, value);
		} else {
			slider.set(ControlMode.PercentOutput, 0);
		}
	}
	public void setSliderPosition(double position){
		slider.set(ControlMode.Position, position);
	}
	public double getSliderPosition(){
		return slider.getSelectedSensorPosition();
	}
}