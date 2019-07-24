package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Arm;
import frc.robot.cargo.constants.ArmPosition;
import frc.robot.hatchpanel.constants.TicksPerInch;
import frc.robot.oi.OI;
import team1351.motionprofile.MotionFrame;
import team1351.motionprofile.TrapezoidalMotionProfile;

public class Angle extends Command {
	private ArmPosition armPosition;
	private TrapezoidalMotionProfile angle;
	private double startPosition;
	private double t;
	public Angle(ArmPosition armPosition) {
		super("Angle");
		requires(Arm.getInstance());
		this.armPosition = armPosition;
	}

	@Override
	protected void initialize() {
		angle = Arm.getInstance().angle(armPosition);
		t = 0;
		startPosition = Arm.getInstance().getArmPosition();
	}

	@Override
	protected void execute() {
		t = timeSinceInitialized();
		MotionFrame frame = angle.getFrameAtTime(t);
		Arm.getInstance().setArmPosition(frame.getPosition());
		System.out.println(frame.getPosition());
	}

	@Override
	protected void end() {
		Arm.getInstance().setArmPosition(angle.getFrameAtTime(t).getPosition());
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return angle.isFinished() || Math.abs(OI.getInstance().getJoystick2().getY()) > 0.1;
	}
}
