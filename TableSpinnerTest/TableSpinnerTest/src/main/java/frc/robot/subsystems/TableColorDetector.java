package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Const;

public class TableColorDetector {

    private static TableColorDetector instance;

    public static TableColorDetector getInstance() {
        if (instance == null)
            instance = new TableColorDetector();
        return instance;
    }

    public enum TableColor {
        Red, Yellow, Blue, Green, Other
    }

    public TableColor state, expectedState;
    private int direction = 0;

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 sensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();

    public static void main(String[] args) {
        TableColor a = TableColor.Blue;
        TableColor b = TableColor.Blue;
        System.out.println(a == TableColor.Blue);
        System.out.println(a == b);
    }

    public TableColorDetector() {
        m_colorMatcher.addColorMatch(Const.kBlue);
        m_colorMatcher.addColorMatch(Const.kRed);
        m_colorMatcher.addColorMatch(Const.kGreen);
        m_colorMatcher.addColorMatch(Const.kYellow);
    }

    public double[] getRawColor() {
        Color detectedColor = sensor.getColor();
        return new double[] { detectedColor.blue, detectedColor.green, detectedColor.blue };
    }

    public TableColor getTableColor() {
        Color detectedColor = sensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        TableColor result = TableColor.Other;
        if (match.color == Const.kBlue) {
            result = TableColor.Blue;
        } else if (match.color == Const.kRed) {
            result = TableColor.Red;
        } else if (match.color == Const.kGreen) {
            result = TableColor.Green;
        } else if (match.color == Const.kYellow) {
            result = TableColor.Yellow;
        }

        return result;
    }

    private String printDoubleArr(double[] arr) {
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
            if (i != arr.length - 1)
                res += ", ";
        }
        return res;
    }

    public boolean updateState() {
        TableColor newState = getTableColor();
        // nothing to update
        if (state == newState) {
            return false;
        }

        // let's check if it's the expected state
        if (newState == expectedState) {
            System.out.println("INFO: Updated color from [" + state + "] to [" + newState + "]");
            state = newState;
            updateExpectedState();
            return true;
        } else if (expectedState == TableColor.Other) {
            System.out.println("INFO: Updated color from [" + state + "] to [" + newState + "]");
            state = newState;
            updateExpectedState();
            return false;
        } else {
            System.out.println("WARNING: Skipped color from [" + state + "] to [" + newState + "]");
            return false;
        }

    }

    private void updateExpectedState() {
        if (direction == 1) {
            if (state == TableColor.Green)
                expectedState = TableColor.Blue;
            else if (state == TableColor.Blue)
                expectedState = TableColor.Yellow;
            else if (state == TableColor.Yellow)
                expectedState = TableColor.Red;
            else if (state == TableColor.Red)
                expectedState = TableColor.Green;
            else // state == Other
                expectedState = TableColor.Other;
        } else { // reverse direction
            if (state == TableColor.Green)
                expectedState = TableColor.Red;
            else if (state == TableColor.Blue)
                expectedState = TableColor.Green;
            else if (state == TableColor.Yellow)
                expectedState = TableColor.Blue;
            else if (state == TableColor.Red)
                expectedState = TableColor.Yellow;
            else // state == Other
                expectedState = TableColor.Other;
        }
    }

    public void init(int direction) {
        this.direction = direction;
        state = TableColor.Other;
        updateExpectedState();
    }

    public void debug() {
        SmartDashboard.putString("Table Color", getTableColor() + "");
        // System.out.println(getTableColor());
        SmartDashboard.putString("Raw Color", printDoubleArr(getRawColor()));
        SmartDashboard.putString("State", state + "");
        SmartDashboard.putString("expectedState", expectedState + "");
    }
}
