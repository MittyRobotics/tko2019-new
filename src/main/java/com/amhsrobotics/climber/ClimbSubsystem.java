package com.amhsrobotics.climber;

import com.amhsrobotics.climber.constants.MotionProfileValues;
import com.amhsrobotics.climber.constants.TalonIds;
import com.amhsrobotics.climber.constants.TicksPerInch;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;
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

    public WPI_TalonSRX rightTalon, leftTalon;
    private DigitalInput limit0, limit1;
    Servo servo = new Servo(0);
    public void initHardware(){
        limit0 = new DigitalInput(0);
        limit1 = new DigitalInput(1);
        leftTalon = new WPI_TalonSRX(TalonIds.CLIMBER_LEFT);
//        WPI_TalonSRX rightTalon = new WPI_TalonSRX(TalonIds.CLIMBER_RIGHT);
//        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
//        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
//        rightTalon.config_kP(0, 0);  // TODO:(masha) PID.CLIMBER[0]
//        rightTalon.config_kI(0, 0);
//        rightTalon.config_kD(0, 0);
//        leftTalon.config_kP(0, 0);
//        leftTalon.config_kI(0, 0);
//        leftTalon.config_kD(0, 0);
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

    public boolean getLimit0(){
        return limit0.get();
    }
    public boolean getLimit1(){
        return limit1.get();
    }
    public void setSpeedSlider(double speed){
        leftTalon.set(ControlMode.PercentOutput, speed);
    }
    public void resetEncoder(){
        leftTalon.set(ControlMode.PercentOutput, -0.5);
        while (limit1.get() && DriverStation.getInstance().isTest())
        {
            System.out.println("Motor Output: " + ClimbSubsystem.getInstance().leftTalon.getMotorOutputPercent());

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        leftTalon.set(ControlMode.PercentOutput, 0);
        System.out.println("Slider position reset: 1/2");
        leftTalon.setSelectedSensorPosition(0);
//        int timer = 0;
//        while (timer < 25 && DriverStation.getInstance().isTest()) {
//            timer ++;
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        leftTalon.set(ControlMode.PercentOutput, 0.5);
//        timer = 0;
//        while (timer < 20 && DriverStation.getInstance().isTest()) {
//            timer ++;
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        leftTalon.set(ControlMode.PercentOutput, -0.3);
        while (limit0.get() && DriverStation.getInstance().isTest()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Slider position reset: 2/2");
        leftTalon.set(ControlMode.PercentOutput, 0);
        leftTalon.setSelectedSensorPosition(0);
        System.out.println(leftTalon.getSelectedSensorPosition());
    }
    public boolean safety() {
        if (getLimit0() == false) {
            leftTalon.set(ControlMode.PercentOutput, -0.5);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("safety");
            return true;
        }
        if (getLimit1() == false) {
            leftTalon.set(ControlMode.PercentOutput, 0.5);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("safety");
            return true;
        }
        else {
            return false;
        }
    }
}
