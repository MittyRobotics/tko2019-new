package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.autonomous.constants.CameraMode;
import frc.robot.autonomous.constants.StreamMode;
import frc.robot.autonomous.vision.Limelight;

public class Robot extends TimedRobot {

  Robot(){
    super(0.06);
  }

  @Override
  public void robotInit() {
    
  }

  @Override
  public void robotPeriodic() {
  
  }

  @Override
  public void autonomousInit() {
   
  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit(){

  }

  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testInit(){

  }

  @Override
  public void testPeriodic() {
    Limelight.getInstance().updateLimelightValues();
    Limelight.getInstance().printValues();

  }
}