package utils.purepursuit;


import frc.robot.autonomous.movement.AutonDriver;
import utils.purepursuit.enums.PathType;
import utils.purepursuit.paths.BezierCurvePath;

public class PathGenerator {
	private static PathGenerator ourInstance = new PathGenerator();

	public static PathGenerator getInstance() {
		return ourInstance;
	}

	private PathGenerator(){
	}

	public Path generate(Waypoint[] waypoints, PathType type, VelocityConstraints velocityConstraints, int steps){
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
