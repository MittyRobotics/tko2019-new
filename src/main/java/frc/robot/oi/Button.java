package frc.robot.oi;

public class Button extends edu.wpi.first.wpilibj.buttons.Button {
	private boolean button;
	Button(boolean button){
		this.button = button;
	}

	@Override
	public boolean get() {
		return button;
	}
}