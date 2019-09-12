package com.amhsrobotics.climber;

import com.amhsrobotics.climber.constants.TalonIds;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Servo;
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

    WPI_TalonSRX rightTalon, leftTalon;
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

    public double getClimberPosition(double position) {
        leftTalon.getSelectedSensorPosition();
        rightTalon.getSelectedSensorPosition();
  }

    public void setClimberPosition(double position) {
        leftTalon.set(ControlMode.Position, position); //TODO: make incremental?
        rightTalon.set(ControlMode.Position, position);
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


    public void moveServo (double angle){
        servo.setAngle(angle);
    }
        // TODO: figure this out
}
