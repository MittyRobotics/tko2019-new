package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Arm;

/**
 * Hold command, which holds the arm at its current position
 * Always follows the Angle command
 */
public class HoldPosition extends Command {
    private double pos;

    /**
     * Constructor for Hold Position Command
     * Sets the command to the Arm subsystem
     * @param pos the position to hold the arm at
     */
    HoldPosition(double pos) {
        super("Angle");
        requires(Arm.getInstance());
        this.pos = pos;
    }

    /**
     * Sets the arm to stay at the set position
     */
    @Override
    protected void initialize() {
        Arm.getInstance().setArmPosition(pos);
    }

    @Override
    protected void execute() {

    }

    /**
     * Sets the motor to stop moving when the command ends
     */
    @Override
    protected void end() {
        Arm.getInstance().manualAngle(0);
    }

    /**
     * Sets the motor to stop moving when command is interrupted
     */
    @Override
    protected void interrupted() {
        end();
    }

    /**
     * Makes the command never end, but it still can be interrupted
     * @return false
     */
    @Override
    protected boolean isFinished() {
        return false;
    }
}
