package com.amhsrobotics.autonomous.movement;

import com.amhsrobotics.autonomous.enums.PathPropertyType;
import com.amhsrobotics.purepursuit.Path;
import com.amhsrobotics.purepursuit.PathGenerator;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;

public class PathProperties {


	private Path path;


	private Waypoint[] waypoints;
	private double lookaheadDist;
	private double kCurvature;
	private double maxAcceleration;
	private double maxDeceleration;
	private double maxVelocity;
	private double startVelocity;
	private double endVelocity;
	private boolean reversed;
	private boolean vision;
	private String name;

	public PathProperties(Waypoint[] waypoints, double lookaheadDist, double kCurvature, double maxAcceleration, double maxDeceleration, double maxVelocity, double startVelocity, double endVelocity, boolean reversed, boolean vision, String name){
		this.waypoints = waypoints;
		this.path = PathGenerator.getInstance().generate(waypoints, PathType.CUBIC_HERMITE_PATH,maxAcceleration,maxDeceleration,maxVelocity,0,0,200);
		this.lookaheadDist = lookaheadDist;
		this.kCurvature = kCurvature;
		this.maxAcceleration = maxAcceleration;
		this.maxDeceleration = maxDeceleration;
		this.maxVelocity = maxVelocity;
		this.startVelocity = startVelocity;
		this.endVelocity = endVelocity;
		this.reversed = reversed;
		this.vision = vision;
		this.name = name;
	}

	public void regeneratePath(){
		this.path = PathGenerator.getInstance().generate(waypoints, PathType.CUBIC_HERMITE_PATH,maxAcceleration,maxDeceleration,maxVelocity,startVelocity,endVelocity,200);
	}

	public Path getPath() {
		regeneratePath();
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public Waypoint[] getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(Waypoint[] waypoints) {
		this.waypoints = waypoints;
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

	public double getProperty(PathPropertyType type){
		if(type == PathPropertyType.KCURVATURE){
			return kCurvature;
		}
		else if(type == PathPropertyType.LOOKAHEAD){
			return lookaheadDist;
		}
		else if(type == PathPropertyType.MAXACCEL){
			return maxAcceleration;
		}
		else if(type==PathPropertyType.MAXDECEL){
			return maxDeceleration;
		}
		else if(type==PathPropertyType.MAXVEL){
			return maxVelocity;
		}
		else{
			return 0;
		}
	}

	public void setProperty(PathPropertyType type, double value){
		if(type == PathPropertyType.KCURVATURE){
			kCurvature = value;
		}
		else if(type == PathPropertyType.LOOKAHEAD){
			lookaheadDist = value;
		}
		else if(type == PathPropertyType.MAXACCEL){
			maxAcceleration = value;
		}
		else if(type==PathPropertyType.MAXDECEL){
			maxDeceleration = value;
		}
		else if(type==PathPropertyType.MAXVEL){
			maxVelocity = value;
		}
	}

	public double getEndVelocity() {
		return endVelocity;
	}

	public void setEndVelocity(double endVelocity) {
		this.endVelocity = endVelocity;
	}


	public boolean isReversed() {
		return reversed;
	}

	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}

	public boolean getReversed(){
		return reversed;
	}
	public boolean getVision() {
		return vision;
	}

	public void setVision(boolean vision) {
		this.vision = vision;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getStartVelocity() {
		return startVelocity;
	}

	public void setStartVelocity(double startVelocity) {
		this.startVelocity = startVelocity;
	}
}
