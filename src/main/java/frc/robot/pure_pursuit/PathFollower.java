package frc.robot.pure_pursuit;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PathFollower {
	Path path;
	double lookaheadDistance =  4;
	double currentLookaheadDistance;
	int previousPointIndex = 0;

	private TradjectoryPoint currentClosestPoint;

	double WHEEL_DISTANCE = 26.5;

	public PathFollower(Path path){
		this.path=path;
	}

	public double[] update(){
		double curvature = calculateCurvature(RobotPose.getInstance().getRobotX(), RobotPose.getInstance().getRobotY(), RobotPose.getInstance().getRobotHeading());
		double targetVelocity = findClosestPoint(RobotPose.getInstance().getRobotX(), RobotPose.getInstance().getRobotY()).getVelocity();

		double leftVel = targetVelocity*(2+curvature*WHEEL_DISTANCE)/2;
		double rightVel = targetVelocity*(2-curvature*WHEEL_DISTANCE)/2;
		SmartDashboard.putNumber("targetVelocity", targetVelocity);
		SmartDashboard.putNumber("curvature", curvature);
		SmartDashboard.putNumber("leftVelocity", leftVel);
		SmartDashboard.putNumber("rightVelocity", rightVel);

		return new double[] {leftVel,rightVel};
	}

	private double calculateCurvature(double robotX, double robotY, double robotHeading){
		double a = -Math.tan(Math.toRadians(robotHeading));
		double b = 1;
		double c = Math.tan(Math.toRadians(robotHeading))*robotX-robotY;
		TradjectoryPoint lookaheadPoint = findLookaheadPoint(robotX, robotY);
		SmartDashboard.putNumber("lookX", lookaheadPoint.getX());
		SmartDashboard.putNumber("lookY", lookaheadPoint.getY());
		double x = Math.abs(a*lookaheadPoint.getX()+b*lookaheadPoint.getY()+c)/Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
		double side = Math.signum(Math.sin(Math.toRadians(robotHeading))*(lookaheadPoint.getX()-robotX)-Math.cos(Math.toRadians(robotHeading))*(lookaheadPoint.getY()-robotY));
		double curvature = 2*x/Math.pow(currentLookaheadDistance,2);
		return curvature*side;
	}



	private TradjectoryPoint findClosestPoint(double x, double y){
		double currentClosest = 1000;
		int index = 0;

		for(int i = 0; i < path.length(); i++){
			if(Math.abs(new TradjectoryPoint(x,y).distance(path.get(i))) < currentClosest){

				currentClosest = Math.abs(new TradjectoryPoint(x,y).distance(path.get(i)));
				index = i;

			}
		}
		previousPointIndex = index;
		currentClosestPoint = path.get(index);
		return path.get(index);
	}
	private TradjectoryPoint findLookaheadPoint(double x, double y){
		double currentClosest = 1000;
		int index = 0;
		TradjectoryPoint closestPoint = findClosestPoint(x,y);
		if(path.get(path.length()-1).distance(closestPoint) <= lookaheadDistance + 1){
			currentLookaheadDistance = lookaheadDistance;
			double a = Math.tan((path.get(path.length()-1).getY()-path.get(path.length()-2).getY())/(path.get(path.length()-1).getX()-path.get(path.length()-2).getX()));
			double x1 = Math.cos(a)*lookaheadDistance;
			double y1 = Math.sin(a)*lookaheadDistance;
			System.out.println("lookahead within dist " + currentLookaheadDistance);
			return new TradjectoryPoint(x1, y1);
		}
		else {
			for (int i = previousPointIndex; i < path.length(); i++) {

				if (Math.abs( path.get(i).distance(new TradjectoryPoint(x, y)) - lookaheadDistance) < currentClosest && path.get(i).distance( new TradjectoryPoint(x, y)) - lookaheadDistance > 0) {
					currentClosest = Math.abs(path.get(i).distance(new TradjectoryPoint(x, y)) - lookaheadDistance);
					currentLookaheadDistance = Math.abs(path.get(i).distance(new TradjectoryPoint(x, y)));
					index = i;
				}
			}
			return path.get(index);
		}

	}

	public TradjectoryPoint getCurrentClosestPoint(){
		return currentClosestPoint;
	}

}
