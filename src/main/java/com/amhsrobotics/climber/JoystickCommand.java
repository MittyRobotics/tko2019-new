package com.amhsrobotics.climber;

import com.amhsrobotics.oi.OI;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickCommand extends Command {
    @Override
    protected boolean isFinished() {
        return false;
    }

    public void execute(){
        double speed = OI.getInstance().getJoystick1().getY();
        ClimberWheel.getInstance().moveWheel(speed);
    }
}
