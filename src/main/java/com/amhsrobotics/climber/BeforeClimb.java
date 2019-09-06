package com.amhsrobotics.climber;

import com.amhsrobotics.cargo.commands.Angle;
import com.amhsrobotics.cargo.constants.ArmPosition;
import com.amhsrobotics.hatchpanel.commands.Slide;
import com.amhsrobotics.hatchpanel.constants.SliderPosition;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BeforeClimb extends CommandGroup {
    public BeforeClimb(){
        addSequential(new Slide(SliderPosition.Middle));
        addSequential(new Angle(ArmPosition.Cargo));
    }
}
