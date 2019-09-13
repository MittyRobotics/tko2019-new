package com.amhsrobotics.climber;

import com.amhsrobotics.climber.constants.TicksPerInch;
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
        leftTalon.getSelectedSensorPosition();
    }

    public double getRightWheel(){
        rightTalon.getSelectedSensorPosition();
    }


    @Override
    protected void initDefaultCommand() {

    }
}
