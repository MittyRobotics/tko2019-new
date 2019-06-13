package frc.robot.hatchpanel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grabber extends Subsystem {
    private static Grabber ourInstance = new Grabber();
    public static Grabber getInstance() { return ourInstance; }

    private Grabber(){

    }

    @Override
    protected void initDefaultCommand() {

    }

    public void Grab(){

    }

    public void Release(){

    }
}