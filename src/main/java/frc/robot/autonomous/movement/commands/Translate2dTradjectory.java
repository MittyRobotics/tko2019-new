package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.constants.TicksPerInch;
import frc.robot.pure_pursuit.*;
import frc.robot.pure_pursuit.enums.PathType;


public class Translate2dTradjectory extends Command {

	Waypoint[] waypoints;
	VelocityConstraints velocityConstraints;
	PathFollower follower;
	PathGenerator generator;
	Path path;

	public Translate2dTradjectory(Waypoint[] waypoints, VelocityConstraints velocityConstraints) {
		super("Translate2dTradjectory");
		requires(DriveTrain.getInstance());
		this.waypoints = waypoints;
		this.velocityConstraints = velocityConstraints;
	}


	@Override
	public void initialize() {
		generator = new PathGenerator(PathType.BEZIER_CURVE_PATH,velocityConstraints);
		path = generator.generate(waypoints,200);
		follower = new PathFollower(path);
	}


	@Override
	public void execute() {
		double[] output = follower.update();
		System.out.println("Left Velocity: " + output[0] + "Right Velocity: " + output[1]);
	}


	@Override
	public void end() {
		System.out.println("end");
	}

	@Override
	public void interrupted() {
		System.out.println("interrupted");
		end();
	}


	@Override
	protected boolean isFinished() {
		return false;
	}
}