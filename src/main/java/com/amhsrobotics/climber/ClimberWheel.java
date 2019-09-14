package com.amhsrobotics.climber;

import com.amhsrobotics.climber.constants.TicksPerInch;
import com.amhsrobotics.hatchpanel.constants.MotionProfileValues;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberWheel extends Subsystem {

    private static ClimberWheel ourInstance = new ClimberWheel();

    public static ClimberWheel getInstance() {
        return ourInstance;
    }

    WPI_TalonSRX rightTalon, leftTalon, frontLeft, frontRight;
    double TicksPerInch = 1;
    private ClimberWheel() {
        WPI_TalonSRX leftTalon = new WPI_TalonSRX(0);
        WPI_TalonSRX rightTalon = new WPI_TalonSRX(1);
        rightTalon.config_kP(0, 0);
        rightTalon.config_kI(0, 0);
        rightTalon.config_kD(0, 0);
        leftTalon.config_kP(0, 0);
        leftTalon.config_kI(0, 0);
        leftTalon.config_kD(0, 0);
        }

    public void setLeftWheel(double position) {
        leftTalon.set(ControlMode.Position, position * TicksPerInch);
    }
    public void setRightWheel(double position) {
        rightTalon.set(ControlMode.Position, position * TicksPerInch);
    }

    public double getLeftWheel(){
        return leftTalon.getSelectedSensorPosition();
    }

    public double getRightWheel(){
        return rightTalon.getSelectedSensorPosition();
    }

    public TrapezoidalMotionProfile wheelLeft(final double position, boolean reversed){
        System.out.println("Slide init pos: " + leftTalon.getSelectedSensorPosition());
        return new TrapezoidalMotionProfile(MotionProfileValues.MAX_ACCELERATION, MotionProfileValues.MAX_VELOCITY,  leftTalon.getSelectedSensorPosition(0)/ com.amhsrobotics.climber.constants.TicksPerInch.WHEEL_TPI, position, 0.06, reversed);
    }

    public TrapezoidalMotionProfile wheelRight(final double position, boolean reversed){
        System.out.println("Slide init pos: " + rightTalon.getSelectedSensorPosition());
        return new TrapezoidalMotionProfile(MotionProfileValues.MAX_ACCELERATION, MotionProfileValues.MAX_VELOCITY,  rightTalon.getSelectedSensorPosition(0)/ com.amhsrobotics.climber.constants.TicksPerInch.WHEEL_TPI, position, 0.06, reversed);
    }



    @Override
    protected void initDefaultCommand() {

    }
}
