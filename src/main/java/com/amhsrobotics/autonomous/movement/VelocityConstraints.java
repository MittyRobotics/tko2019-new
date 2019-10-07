package com.amhsrobotics.autonomous.movement;

public class VelocityConstraints {

	private double maxAcceleration;
	private double maxVelocity;
	private double maxDeceleration;
	public VelocityConstraints(double maxAcceleration, double maxDeceleration, double maxVelocity){
		this.maxAcceleration = maxAcceleration;
		this.maxVelocity = maxVelocity;
		this.maxDeceleration = maxDeceleration;
	}

	public double getMaxAcceleration(){
		return maxAcceleration;
	}
	public double getMaxVelocity(){
		return maxVelocity;
	}
	public double getMaxDeceleration(){
		return maxDeceleration;
	}

}
