package frc.robot.autonomous.constants;

public enum StreamMode {
	Standard(0),
	Main(1),
	Secondary(2);

	public int value;

	StreamMode(int i) {
		value = i;
	}
}
