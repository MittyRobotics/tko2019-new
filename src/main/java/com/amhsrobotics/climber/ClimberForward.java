package com.amhsrobotics.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberForward extends Subsystem {

    private static ClimberForward ourInstance = new ClimberForward();

    public static ClimberForward getInstance() {
        return ourInstance;
    }

    WPI_TalonSRX rightTalon, leftTalon;
    private ClimberForward() {
        WPI_TalonSRX leftTalon = new WPI_TalonSRX(0);
        WPI_TalonSRX rightTalon = new WPI_TalonSRX(1);
    }

    if (j)

    @Override
    protected void initDefaultCommand() {

    }
}
