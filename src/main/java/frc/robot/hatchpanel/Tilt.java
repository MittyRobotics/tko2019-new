package frc.robot.hatchpanel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Tilt extends Subsystem {
    private static Tilt ourInstance = new Tilt();
    public static Tilt getInstance() { return ourInstance; }

    private Tilt(){

    }

    @Override
    protected void initDefaultCommand() {

    }

    public void TiltBack(){

    }

    public void TiltForward(){

    }
}