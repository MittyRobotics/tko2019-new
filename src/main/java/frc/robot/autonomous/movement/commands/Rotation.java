package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;

public class Rotation extends Command {

    double angle; //degrees
    double maxSpeed; //football fields per minute
    RotationDirection direction; //direction of rotation


    public Rotation(double angle, double maxSpeed, RotationDirection direction){
        this.angle = angle;
        this.maxSpeed = maxSpeed;
        this.direction = direction;
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