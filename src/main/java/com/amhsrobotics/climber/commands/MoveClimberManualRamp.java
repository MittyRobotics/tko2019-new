package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import com.amhsrobotics.climber.ClimberWheel;
import com.amhsrobotics.climber.constants.TicksPerInch;
import edu.wpi.first.wpilibj.command.Command;

/*
    PLEASE DO NOT TOUCH ANYTHING HERE
    yes I know it's disgusting
    yes I know it doesn't make sense
    but it wORKS
    SO DON"T TOUCH IT
 */

public class MoveClimberManualRamp extends Command {
    private final double increment = 3;
    private double position, currentPosLeft, currentPosRight;
    public MoveClimberManualRamp(double position){
        super("Move Climber Manual Ramp");
        requires(ClimbSubsystem.getInstance());
        requires(ClimberWheel.getInstance());
        this.position = position;
        currentPosLeft = ClimbSubsystem.getInstance().getClimberPositionLeft();
        currentPosRight = ClimbSubsystem.getInstance().getClimberPositionRight();
    }
    @Override
    protected void initialize(){

    }

    @Override
    protected void execute(){

//        position = ClimberPosition.CLIMB_POS;
        currentPosLeft = ClimbSubsystem.getInstance().getClimberPositionLeft() / TicksPerInch.CLIMBER_TPI;
        currentPosRight = ClimbSubsystem.getInstance().getClimberPositionRight() / TicksPerInch.CLIMBER_TPI;
        ClimberWheel.getInstance().setLeftSpeed(0.1);
        ClimberWheel.getInstance().setRightSpeed(0.1);
        if(position - currentPosLeft < increment){
            ClimbSubsystem.getInstance().setClimberPositionLeft((currentPosLeft + increment) * TicksPerInch.CLIMBER_TPI);
            ClimbSubsystem.getInstance().setClimberPositionRight((currentPosRight + increment) * TicksPerInch.CLIMBER_TPI);
        } else if(currentPosLeft - position  < increment){
            ClimbSubsystem.getInstance().setClimberPositionLeft((currentPosLeft - increment) * TicksPerInch.CLIMBER_TPI);
            ClimbSubsystem.getInstance().setClimberPositionRight((currentPosRight - increment) * TicksPerInch.CLIMBER_TPI);
        } else {
            ClimbSubsystem.getInstance().setClimberPositionLeft(position * TicksPerInch.CLIMBER_TPI);
            ClimbSubsystem.getInstance().setClimberPositionRight(position * TicksPerInch.CLIMBER_TPI);
        }
        System.out.println(currentPosLeft);
        System.out.println(position);
        ClimbSubsystem.getInstance().getTalonCurrent();
    }
    @Override
    protected void end(){
        ClimbSubsystem.getInstance().setClimberPositionLeft(ClimbSubsystem.getInstance().getClimberPositionLeft());
        ClimbSubsystem.getInstance().setClimberPositionRight(ClimbSubsystem.getInstance().getClimberPositionRight());
        ClimberWheel.getInstance().setLeftSpeed(0);
        ClimberWheel.getInstance().setRightSpeed(0);
    }
    @Override
    protected boolean isFinished() {
        System.out.println("Here");
        return Math.abs(position - currentPosLeft - increment) < 0.005 && Math.abs(position - currentPosRight - increment) < 0.005;
    }
}
