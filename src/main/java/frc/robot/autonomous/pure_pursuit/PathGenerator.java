package frc.robot.autonomous.pure_pursuit;

import frc.robot.autonomous.pure_pursuit.enums.PathType;
import frc.robot.autonomous.pure_pursuit.paths.BezierCurvePath;
import frc.robot.autonomous.pure_pursuit.Waypoint;


public class PathGenerator {
	PathType type;
	VelocityConstraints velocityConstraints;
	public PathGenerator(PathType type, VelocityConstraints velocityConstraints){
		this.type = type;
		this.velocityConstraints = velocityConstraints;
	}
	public Path generate(Waypoint[] waypoints, int steps){
		if(type == PathType.BEZIER_CURVE_PATH){
			Path path = new Path(velocityConstraints.getMaxAcceleration(), velocityConstraints.getMaxVelocity(), new BezierCurvePath(waypoints, steps));
			path.generatePath();
			path.calculateDistances();
			path.calculateCurvature();
			path.calculateVelocities();
			return path;
		}
		else{
			return null;
		}
	}

}
