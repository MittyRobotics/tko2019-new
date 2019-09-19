package com.amhsrobotics.climber.commands;

import com.amhsrobotics.cargo.commands.Angle;
import com.amhsrobotics.cargo.constants.ArmPosition;
import com.amhsrobotics.climber.constants.ClimberPosition;
import com.amhsrobotics.hatchpanel.commands.Slide;
import com.amhsrobotics.hatchpanel.constants.SliderPosition;

import com.amhsrobotics.climber.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ButtonOne extends CommandGroup {
    public ButtonOne(){
        addSequential(new Slide(SliderPosition.Middle));
        addSequential(new Angle(ArmPosition.Cargo));
        addSequential(new ServoMoveForward());
        addSequential(new Angle(ArmPosition.Ground));
        addSequential(new MoveClimber3(ClimberPosition.CLIMB_POS));

    }
}
