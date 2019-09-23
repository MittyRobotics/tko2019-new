package com.amhsrobotics.autonomous.movement;

import com.amhsrobotics.purepursuit.Path;
import com.amhsrobotics.purepursuit.PathGenerator;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;

public class PathProperties {


	private Path path;
	private double lookaheadDist;
	private double kCurvature;
	private double maxAcceleration;
	private double maxDeceleration;
	private double maxVelocity;

	public PathProperties(Waypoint[] waypoints, double lookaheadDist, double kCurvature, double maxAcceleration, double maxDeceleration, double maxVelocity){
		this.path = PathGenerator.getInstance().generate(waypoints, PathType.CUBIC_HERMITE_PATH,maxAcceleration,maxDeceleration,maxVelocity,0,0,200);
		this.lookaheadDist = lookaheadDist;
		this.kCurvature = kCurvature;
		this.maxAcceleration = maxAcceleration;
		this.maxDeceleration = maxDeceleration;
		this.maxVelocity = maxVelocity;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public double getLookaheadDist() {
		return lookaheadDist;
	}

	public void setLookaheadDist(double lookaheadDist) {
		this.lookaheadDist = lookaheadDist;
	}

	public double getkCurvature() {
		return kCurvature;
	}

	public void setkCurvature(double kCurvature) {
		this.kCurvature = kCurvature;
	}

	public double getMaxAcceleration() {
		return maxAcceleration;
	}

	public void setMaxAcceleration(double maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}

	public double getMaxDeceleration() {
		return maxDeceleration;
	}

	public void setMaxDeceleration(double maxDeceleration) {
		this.maxDeceleration = maxDeceleration;
	}

	public double getMaxVelocity() {
		return maxVelocity;
	}

	public void setMaxVelocity(double maxVelocity) {
		this.maxVelocity = maxVelocity;
	}


}
