package frc.robot.hatchpanel;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Tilter extends Subsystem {
    private static Tilter ourInstance = new Tilter();
    public static Tilter getInstance() { return ourInstance; }

    private Tilter(){

    }

    @Override
    protected void initDefaultCommand() {

    }

    public void tiltBack(){

    }

    public void tiltForward(){

    }
}