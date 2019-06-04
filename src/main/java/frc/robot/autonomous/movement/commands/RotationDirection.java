package frc.robot.autonomous.movement.commands;

public enum RotationDirection {
    Right(0),
    Left(1);

    public int value;

    RotationDirection(int i) {
        value = i;
    }
}
