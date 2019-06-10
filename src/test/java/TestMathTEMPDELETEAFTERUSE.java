import edu.wpi.first.wpilibj.PIDController;
import frc.robot.drive.constants.PID;

public class TestMathTEMPDELETEAFTERUSE {
	public static void main(String... args){
		double distance = 34;
		double xOffset = 15;
		double skew = 15;
		double angle = -9;

		double stoppingDistance = 12;

		double d = Math.sqrt((xOffset * xOffset) + (distance * distance));

		double d1 = Math.sqrt((xOffset * xOffset) + ((distance-stoppingDistance) * (distance-stoppingDistance)));

		double x = d * Math.sin(Math.toRadians(angle));

		double x1 = stoppingDistance * Math.sin(Math.toRadians(skew));

		double x2 = x1 + x;

		double a2 = Math.toDegrees(Math.asin(x2/d1));

		double gyroAngle = 145;

		//Set gyro angle to be relative to starting position
		gyroAngle = gyroAngle - 145;

		//set gyro value to be relative to target angle
		gyroAngle = gyroAngle + 15;

		gyroAngle = (gyroAngle+180)%360-180;
		System.out.println(gyroAngle);
	}
}
