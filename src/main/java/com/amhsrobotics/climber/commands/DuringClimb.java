package com.amhsrobotics.climber.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DuringClimb extends CommandGroup {
    public DuringClimb() {
        addSequential(new MoveClimberDown());
        addSequential(new MoveClimber());
    }
}
