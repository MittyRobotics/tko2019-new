package frc.robot.autonomous.vision;

import frc.robot.autonomous.enums.VisionEndType;

public class VisionEnd {
	private static VisionEnd ourInstance = new VisionEnd();

	public static VisionEnd getInstance() {
		return ourInstance;
	}

	private double highDeltaTimer;
	private double highDeltaCooldown = 10;
	private double lowDeltaTimer;
	private double lowDeltaCooldown = 10;
	private double failedCalculationsTimer;
	private double failedCalculationsCooldown = 10;
	private double reachedTargetTimer;
	private double reachedTargetCooldown = 1;
	private double lostTargetTimer;
	private double lostTargetCooldown = 10;

	private double prevYaw;
	private double prevDist;

	private VisionEndType[] checkTypes;
	private VisionEnd() {
	}

	public void reset(VisionEndType[] types) {
		highDeltaTimer = 0;
		lowDeltaTimer = 0;
		failedCalculationsTimer = 0;
		reachedTargetTimer = 0;
		lostTargetTimer = 0;

		prevDist = -1000;
		prevYaw = -1000;

		checkTypes = types;
	}

	public void setHighDeltaCooldown(double cooldown) {
		this.highDeltaCooldown = cooldown;
	}

	public void setLowDeltaCooldown(double cooldown) {
		this.lowDeltaCooldown = cooldown;
	}

	public void setFailedCalculationsCooldown(double cooldown) {
		this.failedCalculationsCooldown = cooldown;
	}

	public void setReachedTargetCooldown(double cooldown) {
		this.reachedTargetCooldown = cooldown;
	}

	public void setLostTargetCooldown(double cooldown) {
		this.lostTargetCooldown = cooldown;
	}

	public boolean isFinished(){
		boolean finished = false;
		for (VisionEndType type : checkTypes) {
			if (type == VisionEndType.HIGH_DELTA) {
				finished = finished || highDeltaTimer > highDeltaCooldown;
			} else if (type == VisionEndType.LOW_DELTA) {
				finished = finished || lowDeltaTimer > lowDeltaCooldown;
			} else if (type == VisionEndType.FAILED_CALCULATIONS) {
				finished = finished || failedCalculationsTimer > failedCalculationsCooldown;
			} else if (type == VisionEndType.REACHED_TARGET) {
				finished = finished || reachedTargetTimer > reachedTargetCooldown;
			} else if (type == VisionEndType.LOST_TARGET) {
				finished = finished || lostTargetTimer > lostTargetCooldown;
			}
		}
		return finished;
	}

	public boolean isSafeToUpdate(){
		return highDeltaTimer == 0 && failedCalculationsTimer == 0 &&  lostTargetTimer == 0;
	}


	public boolean update(double yaw, double distance, double targetAngle, double desiredDistance) {
		if (this.prevYaw == -1000) {
			this.prevYaw = yaw;
		}
		if (this.prevDist == -1000) {
			this.prevDist = distance;
		}

		for (VisionEndType type : checkTypes) {
			if (type == VisionEndType.HIGH_DELTA) {
				checkForHighDelta(yaw, prevYaw, distance, prevDist);
			} else if (type == VisionEndType.LOW_DELTA) {
				checkForLowDelta(distance, prevDist);
			} else if (type == VisionEndType.FAILED_CALCULATIONS) {
				checkForFailedCalculations(targetAngle);
			} else if (type == VisionEndType.REACHED_TARGET) {
				checkForReachedTarget(distance, desiredDistance);
			} else if (type == VisionEndType.LOST_TARGET) {
				checkForLostTarget();
			}
		}

		this.prevYaw = yaw;
		this.prevDist = distance;

		return highDeltaTimer > highDeltaCooldown || lowDeltaTimer > lowDeltaCooldown || failedCalculationsTimer > failedCalculationsCooldown || reachedTargetTimer > reachedTargetCooldown || lostTargetTimer > lostTargetCooldown;
	}

	public String getEndCause(){
		String cause = "";
		if(highDeltaTimer > highDeltaCooldown){
			cause += "high delta";
		}
		if(lowDeltaTimer > lowDeltaCooldown){
			if(cause.equals("")){
				cause += "low delta";
			}
			else{
				cause += ", low delta";
			}

		}
		if(failedCalculationsTimer > failedCalculationsCooldown){
			if(cause.equals("")){
				cause += "failed calculations";
			}
			else{
				cause += ", failed calculations";
			}

		}
		if(reachedTargetTimer > reachedTargetCooldown){
			if(cause.equals("")){
				cause += "reached target";
			}
			else{
				cause += ", reached target";
			}

		}
		if(lostTargetTimer > lostTargetCooldown){
			if(cause.equals("")){
				cause += "lost target";
			}
			else{
				cause += ", lost target";
			}

		}
		return cause;
	}

	private void checkForHighDelta(double yaw, double prevYaw, double distance, double prevDist) {
		if (Math.abs(yaw - prevYaw) > 8 || Math.abs(distance - prevDist) > 10) {
			System.out.println("Vision target position delta is too high. Checking for end: " + highDeltaTimer + "/" + highDeltaCooldown);
			highDeltaTimer++;
		} else {
			highDeltaTimer = 0;
		}
	}

	private void checkForLowDelta(double distance, double prevDist) {
		if (Math.abs(distance - prevDist) < .2) {
			System.out.println("Vision target position delta is too low. Checking for end: " + lowDeltaTimer + "/" + lowDeltaCooldown);
			lowDeltaTimer++;
		} else {
			lowDeltaTimer = 0;
		}
	}

	private void checkForLostTarget() {
		if (!Limelight.getInstance().isHasTarget()) {
			System.out.println("Vision target has been lost. Checking for end: " + lostTargetTimer + "/" + lostTargetCooldown);
			lostTargetTimer++;
		} else {
			lostTargetTimer = 0;
		}
	}

	private void checkForFailedCalculations(double targetAngle) {
		if (targetAngle == -1000) {
			failedCalculationsTimer++;
			System.out.println("Vision failed 3D positioning calculations. Checking for end: " + failedCalculationsTimer + "/" + failedCalculationsCooldown);
		}
	}

	private void checkForReachedTarget(double distance, double desiredDistance) {
		if (distance < desiredDistance) {
			System.out.println("Vision has reached the target. Checking for end: " + reachedTargetTimer + "/" + reachedTargetCooldown);
			reachedTargetTimer++;
		} else {
			reachedTargetTimer = 0;
		}
	}


}
