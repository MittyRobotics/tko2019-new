package com.amhsrobotics.autonomous.modes;

import com.amhsrobotics.autonomous.movement.commands.MotionProfileTranslate;
import com.amhsrobotics.autonomous.movement.commands.Translate2dTrajectory;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;
import edu.wpi.first.wpilibj.command.CommandGroup;

import java.awt.geom.Point2D;

public class ExitHabAuto extends CommandGroup {
	public ExitHabAuto() {
		requires(DriveTrain.getInstance());
		addSequential(new Translate2dTrajectory(new Waypoint[]{new Waypoint(new Point2D.Double(0,0), 0),new Waypoint(new Point2D.Double(100,0), 0)}, 50,50,200,PathType.CUBIC_HERMITE_PATH,0,false));
	}
}
