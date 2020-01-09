package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

public class Const {

    // units
    public static final double kTalonRaw2Rot = 1.0 / 4096;
    public static final double kRot2TalonRaw = 4096.0;
    public static final double kSec2Talon100Ms = 1.0 / 10;
    public static final double kTalon100Ms2sec = 10.0;
    public static final double kDeg2Rot = 1.0 / 360;
    public static final double kRot2Deg = 360.0;

    public static final double kIn2M = 0.0254;
    public static final double kM2In = 1 / 0.0254;

    // robot
    public static final Color kBlue = ColorMatch.makeColor(0.143, 0.427, 0.429); // Color(0.12, 0.42, 0.44);
    public static final Color kGreen = ColorMatch.makeColor(0.197, 0.561, 0.240); // Color(0.18, 0.59, 0.23);
    public static final Color kRed = ColorMatch.makeColor(0.561, 0.232, 0.114); // Color(0.5, 0.34, 0.15);
    public static final Color kYellow = ColorMatch.makeColor(0.361, 0.524, 0.113); // Color(0.34, 0.55, 0.11);

    public static final double kEffectiveTableDiameter = 29;
    public static final double kTableRollerDiameter = 3;
    public static final double kTableRot2RollerRot = (Math.PI * kEffectiveTableDiameter)
            / (Math.PI * kTableRollerDiameter);
    public static final double kRollerRot2TableRot = 1 / kTableRot2RollerRot;

    public static final int kTimeout = 30;

    public static final double kTableSliceDeg = 45.0;
    public static final double kTableSliceRollerDeg = kTableSliceDeg * kTableRot2RollerRot;

}