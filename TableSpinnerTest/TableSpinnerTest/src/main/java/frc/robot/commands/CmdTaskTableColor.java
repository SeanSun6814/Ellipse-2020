package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CmdTaskTableColor extends SequentialCommandGroup {
    public CmdTaskTableColor() {
        addCommands(//
                new CmdEngageTableRoller(true), //
                new CmdRotateToColor(), //
                new CmdEngageTableRoller(false)//
        );
    }
}