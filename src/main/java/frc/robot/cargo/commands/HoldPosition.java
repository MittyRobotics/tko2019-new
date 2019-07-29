package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.cargo.Arm;
import frc.robot.cargo.constants.ArmPosition;
import frc.robot.hatchpanel.constants.TicksPerInch;
import frc.robot.oi.OI;
import team1351.motionprofile.MotionFrame;
import team1351.motionprofile.TrapezoidalMotionProfile;

public class HoldPosition extends Command {
    private double pos;
    public HoldPosition(double pos) {
        super("Angle");
        requires(Arm.getInstance());
        this.pos = pos;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Arm.getInstance().setArmPosition(pos);
    }

    @Override
    protected void end() {
        Arm.getInstance().manualAngle(0);
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
