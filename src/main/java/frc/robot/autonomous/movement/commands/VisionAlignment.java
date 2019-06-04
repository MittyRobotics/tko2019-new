package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;

public class VisionAlignment extends Command {

    double maxSpeed; //football fields per minute

    public VisionAlignment(double maxSpeed){
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){

    }

    @Override
    public void end(){

    }

    @Override
    public void interrupted(){
        end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}