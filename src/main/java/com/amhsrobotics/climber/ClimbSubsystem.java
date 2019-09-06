package com.amhsrobotics.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbSubsystem extends Subsystem {
    private static ClimbSubsystem ourInstance = new ClimbSubsystem();

    public static ClimbSubsystem getInstance() {
        return ourInstance;
    }
    WPI_TalonSRX rightTalon, leftTalon;
    double tpi = 0;
    double increment = 0;
    private ClimbSubsystem() {


    }

    public void initHardware(){
        WPI_TalonSRX leftTalon = new WPI_TalonSRX(0);
        WPI_TalonSRX rightTalon = new WPI_TalonSRX(1);
        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        rightTalon.config_kP(0, 0);
        rightTalon.config_kI(0, 0);
        rightTalon.config_kD(0, 0);
        leftTalon.config_kP(0, 0);
        leftTalon.config_kI(0, 0);
        leftTalon.config_kD(0, 0);

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

    public void moveClimber(double position) {
        double setpoint = position*tpi;


        while ((rightTalon.getSelectedSensorPosition() != setpoint) && (leftTalon.getSelectedSensorPosition() != setpoint)){
            if (setpoint - rightTalon.getSelectedSensorPosition() >= increment) {
                rightTalon.set(ControlMode.Position, rightTalon.getSelectedSensorPosition() + increment);
            } else {
                rightTalon.set(ControlMode.Position, setpoint);
            }

            if (setpoint - leftTalon.getSelectedSensorPosition() >= increment) {
                leftTalon.set(ControlMode.Position, leftTalon.getSelectedSensorPosition() + increment);
            } else {
                leftTalon.set(ControlMode.Position, setpoint);
            }
        }
    }
}
