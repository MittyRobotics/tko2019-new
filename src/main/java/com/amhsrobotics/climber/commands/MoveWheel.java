package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimberWheel;
import com.amhsrobotics.climber.constants.TicksPerInch;
import com.amhsrobotics.climber.constants.WheelPosition;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.motionprofile.MotionFrame;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;
import com.amhsrobotics.oi.OI;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

public class MoveWheel extends Command {
    public MoveWheel() {
        super("ClimberWheel");
        requires(DriveTrain.getInstance());
        requires(ClimberWheel.getInstance());
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        ClimberWheel.getInstance().setLeftSpeed(0.1);
        ClimberWheel.getInstance().setRightSpeed(0.1);
        DriveTrain.getInstance().tankDrive(0.1, 0.1);
    }

    @Override
    protected void end(){
        ClimberWheel.getInstance().setLeftSpeed(0);
        ClimberWheel.getInstance().setRightSpeed(0);
        DriveTrain.getInstance().tankDrive(0, 0);
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(OI.getInstance().getXboxController().getY(GenericHID.Hand.kLeft)) > 0.1
                || Math.abs(OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight))
                > 0.1;
    }
}
