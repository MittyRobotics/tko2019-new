package frc.robot.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.hatchpanel.Slider;

public class SliderManual extends Command {

    public SliderManual(){
        requires(Slider.getInstance());
    }

    @Override
    protected void initialize(){

    }

    @Override
    protected void execute(){

    }

    @Override
    protected void end(){

    }

    @Override
    protected void interrupted(){
        end();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}