package com.amhsrobotics.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.cargo.Arm;

/**
 * Calibrate Arm command to reset the encoder before match
 */
public class CalibrateArm extends Command {
	private CalibrateState state;
	private double t;

	/**
	 * Constructor for  the Calibrate Arm Command
	 * Sets the command to run on the arm subsystem
	 */
	public CalibrateArm() {
		super("CalibrateArm");
		requires(Arm.getInstance());
	}

	@Override
	protected void initialize() {
		state = CalibrateState.Up1;
		t = 0;
	}

	@Override
	protected void execute() {
		switch (state){
			case Up1:
				Arm.getInstance().manualAngle(-0.3);
				if(Arm.getInstance().isRevLimitSwitchPressed()){
					state = CalibrateState.Stop1;
					t = timeSinceInitialized();
				}
			case Stop1:
				Arm.getInstance().manualAngle(0);
				if(timeSinceInitialized() - t > 0.1){
					state = CalibrateState.Down;
					t = timeSinceInitialized();
				}
			case Down:
				Arm.getInstance().manualAngle(0.2);
				if(timeSinceInitialized() - t > 0.5){
					state = CalibrateState.Stop2;
					t = timeSinceInitialized();
				}
			case Stop2:
				Arm.getInstance().manualAngle(0);
				if(timeSinceInitialized() - t > 0.1){
					state = CalibrateState.Up2;
				}
			case Up2:
				Arm.getInstance().manualAngle(-0.15);
				if(Arm.getInstance().isRevLimitSwitchPressed()){
					state = CalibrateState.End;
				}
			case End:
				Arm.getInstance().manualAngle(0);
		}
	}

	@Override
	protected void end() {
		Arm.getInstance().manualAngle(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return state == CalibrateState.End;
	}
	enum CalibrateState {
		Up1, Stop1, Down, Stop2, Up2, End
	}
}