package frc.robot.hatchpanel;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.hatchpanel.constants.SliderManualState;

public class Slider extends Subsystem {
    private static Slider ourInstance = new Slider();
    public static Slider getInstance() { return ourInstance; }

    private Slider(){

    }

    @Override
    protected void initDefaultCommand() {

    }

    public void sliderAuton(){

    }

    public void sliderManual(SliderManualState sliderManualState){

    }
}