package com.amhsrobotics.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbSubsystem extends Subsystem {
    private static ClimbSubsystem ourInstance = new ClimbSubsystem();

    public static ClimbSubsystem getInstance() {
        return ourInstance;
    }
    WPI_TalonSRX rightTalon, leftTalon;
    private ClimbSubsystem() {
        WPI_TalonSRX leftTalon = new WPI_TalonSRX(0);
        WPI_TalonSRX rightTalon = new WPI_TalonSRX(1);

    }

    @Override
    protected void initDefaultCommand() {

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
}
