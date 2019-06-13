package frc.robot.hatchpanel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.hatchpanel.constants.SolenoidIds;

public class Grabber extends Subsystem {
    private boolean isManual = false;
    private boolean isProcessDone = false;
    private static Grabber ourInstance = new Grabber();
    public static Grabber getInstance() { return ourInstance; }
//    private DoubleSolenoid solSide;
    private final int[] solSide = SolenoidIds.SOL_SIDE_ID;

    private Grabber(){

    }

    @Override
    protected void initDefaultCommand() {

    }

    public void Grab(){
        solSide.set(DoubleSolenoid.Value.kReverse);
    }

    public void Release(){
        solSide.set(DoubleSolenoid.Value.kForward);
    }
}