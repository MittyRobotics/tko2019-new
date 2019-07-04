package frc.robot.cargo;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.cargo.constants.IntakeSpeeds;
import frc.robot.cargo.constants.OuttakeSpeeds;
import frc.robot.cargo.constants.SwitchIds;
import frc.robot.cargo.constants.TalonIds;
import frc.robot.cargo.constants.TalonInversions;

public class Rollers extends Subsystem {
	private static Rollers ourInstance = new Rollers();
	private WPI_TalonSRX topRoller = new WPI_TalonSRX(TalonIds.ROLLERS[0]);
	private WPI_TalonSRX bottomRoller = new WPI_TalonSRX(TalonIds.ROLLERS[1]);
	private DigitalInput ballSensor = new DigitalInput(SwitchIds.BALL_SENSOR); //switch is inverted
	public static Rollers getInstance() {
		return ourInstance;
	}

	private Rollers() {

	}
	public void initHardware(){
		topRoller.setInverted(TalonInversions.ROLLERS[0]);
		bottomRoller.setInverted(TalonInversions.ROLLERS[1]);
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void intake(){
		if(!ballDetected()){
			setRollerSpeeds(IntakeSpeeds.TOP_ROLLER, IntakeSpeeds.BOTTOM_ROLLER);
		} else {
			stopRollers();
		}
	}

	public void outtake(){
		setRollerSpeeds(OuttakeSpeeds.TOP_ROLLER, OuttakeSpeeds.BOTTOM_ROLLER);
	}


	public void stopRollers(){
		setRollerSpeeds(0, 0);
	}

	private void setRollerSpeeds(final double topRollerSpeed, final double bottomRollerSpeed){
		topRoller.set(ControlMode.PercentOutput, topRollerSpeed);
		bottomRoller.set(ControlMode.PercentOutput, bottomRollerSpeed);
	}

	public boolean ballDetected(){
		return !ballSensor.get(); //switch is inverted
	}
}