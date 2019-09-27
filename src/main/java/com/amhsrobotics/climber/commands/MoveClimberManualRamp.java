package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import com.amhsrobotics.climber.constants.ClimberPosition;
import com.amhsrobotics.climber.constants.TicksPerInch;
import edu.wpi.first.wpilibj.command.Command;

public class MoveClimberManualRamp extends Command {
    private final double increment = 3;
    private double position, currentPos;
    public MoveClimberManualRamp(double position){
        super("Move Climber Manual Ramp");
        requires(ClimbSubsystem.getInstance());
        this.position = position;
        currentPos = ClimbSubsystem.getInstance().getClimberPositionLeft();
    }
    @Override
    protected void initialize(){

    }

    @Override
    protected void execute(){
//        position = ClimberPosition.CLIMB_POS;
        currentPos = ClimbSubsystem.getInstance().getClimberPositionLeft() / TicksPerInch.CLIMBER_TPI;
        if(position - currentPos < increment){
            ClimbSubsystem.getInstance().setClimberPositionLeft((currentPos + increment) * TicksPerInch.CLIMBER_TPI);
        } else if(currentPos - position  < increment){
            ClimbSubsystem.getInstance().setClimberPositionLeft((currentPos - increment) * TicksPerInch.CLIMBER_TPI);
        } else {
            ClimbSubsystem.getInstance().setClimberPositionLeft(position * TicksPerInch.CLIMBER_TPI);
        }
        System.out.println(currentPos);
        System.out.println(position);
        ClimbSubsystem.getInstance().getTalonCurrent();
    }
    @Override
    protected void end(){
        ClimbSubsystem.getInstance().setClimberPositionLeft(ClimbSubsystem.getInstance().getClimberPositionLeft());
    }
    @Override
    protected boolean isFinished() {
        System.out.println("Here");
        return Math.abs(position - currentPos - increment) < 0.005;
    }
}
