package com.amhsrobotics.climber;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

public class ClimbSubsystem extends Subsystem {
    private static ClimbSubsystem ourInstance = new ClimbSubsystem();

    public static ClimbSubsystem getInstance() {
        return ourInstance;
    }

    private ClimbSubsystem() {
    }

    @Override
    protected void initDefaultCommand() {

    }
    WPI_TalonSRX leftTalon = new WPI_TalonSRX(0);
    WPI_TalonSRX rightTalon = new WPI_TalonSRX(1);

    DigitalInput limitSwitch;

    limitSwitch = DigitalInput;

    public void resetEncoder(){
        leftTalon.setSelectedSensorPosition(0);
        rightTalon.setSelectedSensorPosition(0)
    }
}
