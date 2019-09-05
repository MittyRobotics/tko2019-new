package com.amhsrobotics.climber;

import edu.wpi.first.wpilibj.command.Subsystem;

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

}
