package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.constants.TicksPerInch;


public class TestCommand extends Command {

    public TestCommand() {
        super("TestCommand");
        System.out.println("TestCommand");
    }


    @Override
    public void initialize() {
        System.out.println("initialize");
    }


    @Override
    public void execute() {
        System.out.println("execute");
    }


    @Override
    public void end() {
        System.out.println("end");
    }

    @Override
    public void interrupted() {
        System.out.println("interrupted");
        end();
    }


    @Override
    protected boolean isFinished() {
        return false;
    }
}