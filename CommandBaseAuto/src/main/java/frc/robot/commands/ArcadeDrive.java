package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class ArcadeDrive extends CommandBase {
  private final Drive drive;
  private final DoubleSupplier joystickAxis1, joystickAxis4;

  public ArcadeDrive(Drive drive, DoubleSupplier joystickAxis1, DoubleSupplier joystickAxis4) {
    this.joystickAxis1 = joystickAxis1;
    this.joystickAxis4 = joystickAxis4;
    this.drive = drive;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = -joystickAxis1.getAsDouble() * 0.6;
    double turn = joystickAxis4.getAsDouble() * 0.3;

    double left = speed + turn;
    double right = speed - turn;

    drive.turnMotors(left, right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
