package frc.robot.hatchpanel;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.hatchpanel.constants.SolenoidIds;

public class Grabber extends Subsystem {
    private static Grabber ourInstance = new Grabber();
    public static Grabber getInstance() { return ourInstance; }
    private DoubleSolenoid grabSolenoid = new DoubleSolenoid(SolenoidIds.GRAB_SOLENOID_IDS[0], SolenoidIds.GRAB_SOLENOID_IDS[1]);

    private Grabber(){
        super("Grabber");
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void grab(){
        grabSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void release(){
        grabSolenoid.set(DoubleSolenoid.Value.kForward);
    }
}