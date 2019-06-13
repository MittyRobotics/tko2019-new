package frc.robot.hatchpanel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Slider extends Subsystem {
    private static Slider ourInstance = new Slider();
    public static Slider getInstance() { return ourInstance; }

    private Slider(){

    }

    @Override
    protected void initDefaultCommand() {

    }

    public void SliderAuton(){

    }

    public void SliderManual(){

    }
}