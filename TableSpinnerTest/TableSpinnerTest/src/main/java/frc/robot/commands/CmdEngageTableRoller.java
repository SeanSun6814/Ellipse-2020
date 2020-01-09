package frc.robot.commands;

import frc.robot.subsystems.TableSpinner;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CmdEngageTableRoller extends CommandBase {
  private final TableSpinner tableSpinner = TableSpinner.getInstance();
  private boolean engageRoller;

  public CmdEngageTableRoller(boolean engageRoller) {
    this.engageRoller = engageRoller;
  }

  @Override
  public void initialize() {
    tableSpinner.engageRoller(engageRoller);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
