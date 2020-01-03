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

	/**
	 * Initializes the first state of the state machine
	 */
	@Override
	protected void initialize() {
		state = CalibrateState.Up1;
		t = 0;
		Arm.getInstance().manualAngle(-0.3);
	}

	/**
	 * Runs the state machine until it reaches the end state
	 */
	@Override
	protected void execute() {
		switch (state){
			case Up1:
				if(Arm.getInstance().isRevLimitSwitchPressed()){
					Arm.getInstance().manualAngle(0);
					state = CalibrateState.Stop1;
					t = timeSinceInitialized();
				}
			case Stop1:
				if(timeSinceInitialized() - t > 0.1){
					Arm.getInstance().manualAngle(0.2);
					state = CalibrateState.Down;
					t = timeSinceInitialized();
				}
			case Down:
				if(timeSinceInitialized() - t > 0.5){
					Arm.getInstance().manualAngle(0);
					state = CalibrateState.Stop2;
					t = timeSinceInitialized();
				}
			case Stop2:
				if(timeSinceInitialized() - t > 0.1){
					Arm.getInstance().manualAngle(-0.15);
					state = CalibrateState.Up2;
				}
			case Up2:
				if(Arm.getInstance().isRevLimitSwitchPressed()){
					Arm.getInstance().manualAngle(0);
					state = CalibrateState.End;
				}
		}
	}

	/**
	 * Sets the arm to stop moving when the reset ends
	 */
	@Override
	protected void end() {
		Arm.getInstance().manualAngle(0);
	}

	/**
	 * Sets the arm to stop moving when the reset is interrupted
	 */
	@Override
	protected void interrupted() {
		end();
	}

	/**
	 * Tells if the state machine is finished
	 * @return if state reaches the end state
	 */
	@Override
	protected boolean isFinished() {
		return state == CalibrateState.End;
	}

	/**
	 * The Enum with all of the different states in the state machine
	 */
	enum CalibrateState {
		Up1, Stop1, Down, Stop2, Up2, End
	}
}