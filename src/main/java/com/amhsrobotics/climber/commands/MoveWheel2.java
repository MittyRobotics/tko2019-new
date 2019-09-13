package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimberWheel;
import com.amhsrobotics.climber.constants.TicksPerInch;
import com.amhsrobotics.climber.constants.WheelPosition;
import com.amhsrobotics.hatchpanel.Slider;
import com.amhsrobotics.motionprofile.MotionFrame;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;
import edu.wpi.first.wpilibj.command.Command;

public class MoveWheel2 extends Command {
    private TrapezoidalMotionProfile wheelLeft, wheelRight;
    private double t, position;
    public MoveWheel2(double position) {
        super("ClimberWheel");
        this.position = position;
        requires(ClimberWheel.getInstance());
        System.out.println("construct");
    }

    @Override
    protected void initialize() {
        System.out.println("init");
        wheelLeft = ClimberWheel.getInstance().wheelLeft(WheelPosition.WHEEL_POS);
        wheelRight = ClimberWheel.getInstance().wheelRight(WheelPosition.WHEEL_POS);
        t = 0;
    }

    @Override
    protected void execute() {
        t = timeSinceInitialized();
        MotionFrame frameRight = wheelRight.getFrameAtTime(t);
        MotionFrame frameLeft = wheelLeft.getFrameAtTime(t);
        Slider.getInstance().setSliderPosition(frameRight.getPosition() * TicksPerInch.WHEEL_TPI);
        System.out.println(frameRight.getPosition());
        Slider.getInstance().setSliderPosition(frameLeft.getPosition() * TicksPerInch.WHEEL_TPI);
        System.out.println(frameLeft.getPosition());
    }

    @Override
    protected void end(){
        System.out.println("end");
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return (wheelLeft.isFinished() && wheelRight.isFinished());
    }
}
