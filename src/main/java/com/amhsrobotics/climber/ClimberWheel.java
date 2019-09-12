package com.amhsrobotics.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberWheel extends Subsystem {

    private static ClimberWheel ourInstance = new ClimberWheel();

    public static ClimberWheel getInstance() {
        return ourInstance;
    }

    WPI_TalonSRX rightTalon, leftTalon, frontLeft, frontRight;
    private ClimberWheel() {
        WPI_TalonSRX leftTalon = new WPI_TalonSRX(0);
        WPI_TalonSRX rightTalon = new WPI_TalonSRX(1);
        WPI_TalonSRX frontLeft = new WPI_TalonSRX(2); //TODO: use actual drive code?
        WPI_TalonSRX frontRight = new WPI_TalonSRX(3);
    }

    public void moveWheel(double yVal){ //TODO: make this PID
        if (Math.abs(yVal) > 0.05 ) {
            leftTalon.set(ControlMode.PercentOutput, yVal);
            rightTalon.set(ControlMode.PercentOutput, yVal);
            frontRight.set(ControlMode.PercentOutput, yVal);
            frontLeft.set(ControlMode.PercentOutput, yVal);
        } else  {
            leftTalon.set(0);
            rightTalon.set(0);
            frontLeft.set(0);
            frontRight.set(0);
        }

    }

    @Override
    protected void initDefaultCommand() {

    }
}
