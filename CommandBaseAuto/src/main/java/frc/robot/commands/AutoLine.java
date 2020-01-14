package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class AutoLine extends CommandBase {
  private final Drive drive;
  private double startingTime = 0;
  private final double duration, speed;
  private boolean isFinished = false;

  public AutoLine(Drive drive, double duration, double speed) {
    this.duration = duration;
    this.speed = speed;
    this.drive = drive;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isFinished = false;
    startingTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.turnMotors(speed, speed);

    double currentTime = Timer.getFPGATimestamp();
    double dt = currentTime - startingTime;

    if (dt >= duration)
      isFinished = true;

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
