package com.amhsrobotics.climber;

import com.amhsrobotics.climber.constants.MotionProfileValues;
import com.amhsrobotics.climber.constants.TalonIds;
import com.amhsrobotics.climber.constants.TicksPerInch;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbSubsystem extends Subsystem {
    public enum ClimberPosition {
        BOTTOM, TOP
    }

// TODO: Owen's Trapezoid thing?

    private static ClimbSubsystem ourInstance = new ClimbSubsystem();

    public static ClimbSubsystem getInstance() {
        return ourInstance;
    }

    public ClimbSubsystem() {
        super("Climber");
    }

    private WPI_TalonSRX rightTalon, leftTalon;
    Servo servo = new Servo(0);
    public void initHardware(){
        WPI_TalonSRX leftTalon = new WPI_TalonSRX(TalonIds.CLIMBER_LEFT);
        WPI_TalonSRX rightTalon = new WPI_TalonSRX(TalonIds.CLIMBER_RIGHT);
        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        rightTalon.config_kP(0, 0);  // TODO:(masha) PID.CLIMBER[0]
        rightTalon.config_kI(0, 0);
        rightTalon.config_kD(0, 0);
        leftTalon.config_kP(0, 0);
        leftTalon.config_kI(0, 0);
        leftTalon.config_kD(0, 0);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public double getClimberPositionLeft() {
        return leftTalon.getSelectedSensorPosition() / TicksPerInch.CLIMBER_TPI;
  }

    public double getClimberPositionRight() {
        return rightTalon.getSelectedSensorPosition() / TicksPerInch.CLIMBER_TPI;
    }

    //TODO: figure out the relation between the two sides - two encoders? one master encoder?

    public void setClimberPositionLeft(double position) {
        leftTalon.set(ControlMode.Position, position * TicksPerInch.CLIMBER_TPI); //TODO: make incremental
    }
    public void setClimberPositionRight(double position) {
        rightTalon.set(ControlMode.Position, position * TicksPerInch.CLIMBER_TPI);
    }
    public void resetEncoder(){
        while (!leftTalon.getSensorCollection().isFwdLimitSwitchClosed()) {
            leftTalon.set(ControlMode.PercentOutput, .1);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!rightTalon.getSensorCollection().isFwdLimitSwitchClosed()) {
            rightTalon.set(ControlMode.PercentOutput, .1);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        rightTalon.set(ControlMode.PercentOutput, 0);
        leftTalon.set(ControlMode.PercentOutput, 0);
        rightTalon.setSelectedSensorPosition(0);
        leftTalon.setSelectedSensorPosition(0);
    }

    public TrapezoidalMotionProfile climberLeft(final double position, boolean reversed){
        System.out.println("Slide init pos: " + leftTalon.getSelectedSensorPosition());
        return new TrapezoidalMotionProfile(MotionProfileValues.MAX_ACCELERATION, MotionProfileValues.MAX_VELOCITY,  leftTalon.getSelectedSensorPosition(0)/ com.amhsrobotics.climber.constants.TicksPerInch.WHEEL_TPI, position, 0.06, reversed);
    }

    public TrapezoidalMotionProfile climberRight(final double position, boolean reversed){
        System.out.println("Slide init pos: " + rightTalon.getSelectedSensorPosition());
        return new TrapezoidalMotionProfile(MotionProfileValues.MAX_ACCELERATION, MotionProfileValues.MAX_VELOCITY,  rightTalon.getSelectedSensorPosition(0)/ com.amhsrobotics.climber.constants.TicksPerInch.WHEEL_TPI, position, 0.06, reversed);
    }

    public void moveServo (double angle){
        servo.set(angle);
    }

}
