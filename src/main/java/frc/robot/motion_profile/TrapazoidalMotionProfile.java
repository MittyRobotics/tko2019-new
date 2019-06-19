package frc.robot.motion_profile;

public class TrapazoidalMotionProfile {
	private MotionSegment accelerationSegment;
	private MotionSegment cruiseSegment;
	private MotionSegment decelerationSegment;

	private double maxAcceleration;
	private double maxVelocity;
	private double setpoint;
	private int steps;
	private double tTotal;

	private double prevVelocity =0;
	private double prevTime =0;

	private boolean done = false;

	public TrapazoidalMotionProfile(double maxAcceleration, double maxVelocity, double setpoint, int steps){

		this.done = false;
		this.maxAcceleration = maxAcceleration;
		this.setpoint = setpoint;
		this.steps = steps;

		double theoreticalTTotal = Math.sqrt(setpoint/maxAcceleration);
		double theoreticalMaxVelocity = theoreticalTTotal*maxAcceleration;
		double tAccel = maxVelocity/maxAcceleration;
		double tDecel = maxVelocity/maxAcceleration;
		double dAccel = maxVelocity*tAccel/2;
		double dDecel = maxVelocity*tDecel/2;
		double dCruise = setpoint-dAccel-dDecel;
		double tCruise = dCruise/maxVelocity;
		double tTotal = tAccel + tDecel + tCruise;
		double newMaxVelocity = maxVelocity;

		if(dCruise <= 0){
			tAccel = theoreticalMaxVelocity/maxAcceleration;
			tDecel = theoreticalMaxVelocity/maxAcceleration;
			dAccel = theoreticalMaxVelocity*tAccel/2;
			dDecel = theoreticalMaxVelocity*tDecel/2;
			tCruise = 0;
			dCruise = 0;
			newMaxVelocity = theoreticalMaxVelocity;
			tTotal = tAccel+tDecel;
		}

		this.maxVelocity = newMaxVelocity;
		this.tTotal = tTotal;

		accelerationSegment = new MotionSegment(tAccel,dAccel);
		cruiseSegment = new MotionSegment(tCruise,dCruise);
		decelerationSegment = new MotionSegment(tDecel,dDecel);
	}

	public double stepsToTime(int _steps){
		return tTotal /steps * _steps;
	}

	public double timeToSteps(double t){
		return t / (tTotal /steps);
	}

	public MotionFrame getFrameAtTime(double t){

		double velocity = getVelocityAtTime(t, maxAcceleration, maxVelocity, accelerationSegment, cruiseSegment);
		double position = getPositionAtTime(t,velocity, maxVelocity, maxAcceleration,accelerationSegment,cruiseSegment);

		double acceleration = getAccelerationAtTime(velocity, prevVelocity,t, prevTime);
		this.prevVelocity = velocity;
		this.prevTime = t;
		if(t >= tTotal){
			done = true;
			return new MotionFrame(setpoint,0,0, tTotal);
		}
		return new MotionFrame(position,velocity,acceleration,t);
	}

	private double getVelocityAtTime(double _t, double _acceleration, double _maxVelocity, MotionSegment _accelerationSegment, MotionSegment _cruiseSegment){
		double output = 0;

		double _tAccel = _accelerationSegment.getT();
		double _tCruise = _cruiseSegment.getT();
		if(_t < _tAccel){
			output = _t* _acceleration;
		}
		else if(_t < _tCruise +  _tAccel){
			output = _maxVelocity;
		}
		else{
			output = _maxVelocity-(_t- _tAccel-_tCruise)*_acceleration;
		}
		return output;
	}

	private double getPositionAtTime(double _t, double _velocity, double _maxVelocity, double _acceleration, MotionSegment _accelerationSegment, MotionSegment _cruiseSegment){
		double output=0;
		double _tAccel = _accelerationSegment.getT();
		double _dAccel = _accelerationSegment.getDistance();
		double _tCruise = _cruiseSegment.getT();
		double _dCruise = _cruiseSegment.getDistance();
		if(_t < _tAccel){
			output = _t*_velocity/2;
		}
		else if(_t < _tAccel + _tCruise){
			output = _dAccel;
			output += _maxVelocity*(_t-_tAccel);
		}
		else{
			output = _dAccel + _dCruise;
			_t = _t-(_tCruise+_tAccel);
			output += _velocity*_t;
			output += (_maxVelocity-_velocity) * _t/2;
		}
		return output;
	}

	private double getAccelerationAtTime(double _velocity, double _prevVelocity, double _t, double _prevTime){
		return (_velocity-_prevVelocity)/(_t-_prevTime);
	}

	public int getSteps(){
		return steps;
	}
	public double getSetpoint(){
		return setpoint;
	}
	public double getMaxAcceleration(){
		return maxAcceleration;
	}
	public double getMaxVelocity(){
		return maxVelocity;
	}
	public double gettTotal(){
		return tTotal;
	}
	public double getPrevVelocity(){
		return prevVelocity;
	}
	public double getPrevTime(){
		return prevTime;
	}
	public boolean isDone(){
		return done;
	}
}
