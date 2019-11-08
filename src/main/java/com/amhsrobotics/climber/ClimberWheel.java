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

    public static WPI_TalonSRX rightTalon, leftTalon;
    private double TicksPerInch = 1;
    public ClimberWheel() {
        leftTalon = new WPI_TalonSRX(40);
        rightTalon = new WPI_TalonSRX(43);
    }



    @Override
    protected void initDefaultCommand() {

    }
    public void setLeftSpeed(double speed){
        leftTalon.set(ControlMode.PercentOutput, speed);
    }
    public void setRightSpeed(double speed){
        rightTalon.set(ControlMode.PercentOutput, speed);
    }
}
