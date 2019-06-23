package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
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
	boolean finished= false;

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
		RobotPose.getInstance().resetPosition();
	}


	@Override
	public void execute() {
		RobotPose.getInstance().update();
		double[] output = follower.update();

		output[0] = output[0]+6;
		output[1] = output[1]+6;
		System.out.println("Left Velocity: " + output[0] + "Right Velocity: " + output[1] + " X: " + RobotPose.getInstance().getRobotX() + " Y: " + RobotPose.getInstance().getRobotY() + " Angle: " + RobotPose.getInstance().getRobotHeading());

		DriveTrain.getInstance().tankVelocity(output[0], output[1]);
		if( Math.abs(RobotPose.getInstance().getRobotX() - path.get(path.length()-1).getX()) < 2 && Math.abs(RobotPose.getInstance().getRobotY() - path.get(path.length()-1).getY()) < 2){
			finished = true;
		}
	}


	@Override
	public void end() {
		DriveTrain.getInstance().tankDrive(0,0);
		System.out.println("end");
	}

	@Override
	public void interrupted() {
		System.out.println("interrupted");
		end();
	}


	@Override
	protected boolean isFinished() {
		return finished;
	}
}