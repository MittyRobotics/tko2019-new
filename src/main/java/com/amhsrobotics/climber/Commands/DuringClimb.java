package com.amhsrobotics.climber.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DuringClimb extends CommandGroup {
    public DuringClimb() {
        addSequential(new MoveClimberDown());
        addSequential(new MoveClimberUp());
    }
}
