package frc.robot.cargo;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	private static Arm ourInstance = new Arm();
	public static Arm getInstance() {
		return ourInstance;
	}

	private Arm() {
	}

	@Override
	protected void initDefaultCommand() {

	}

}
