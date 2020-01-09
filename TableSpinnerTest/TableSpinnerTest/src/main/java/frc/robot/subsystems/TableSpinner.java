package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.subsystems.TableColorDetector.TableColor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Const;

public class TableSpinner extends SubsystemBase {
    private static TableSpinner instance;

    public static TableSpinner getInstance() {
        if (instance == null)
            instance = new TableSpinner();
        return instance;
    }

    private final DoubleSolenoid piston = new DoubleSolenoid(0, 1);

    private final TalonSRX motor = new TalonSRX(7);
    private final TableColorDetector tableColorDetector = TableColorDetector.getInstance();

    public TableSpinner() {
        int pidIdx = 0;
        motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        motor.setSelectedSensorPosition(0);
        motor.setSensorPhase(true);
        motor.selectProfileSlot(pidIdx, pidIdx);
        motor.config_kF(pidIdx, 0.1, Const.kTimeout);
        motor.config_kP(pidIdx, 0, Const.kTimeout);
        motor.config_kI(pidIdx, 0, Const.kTimeout);
        motor.config_kD(pidIdx, 0, Const.kTimeout);
        motor.configMotionCruiseVelocity(
                (int) (0.75 * Const.kTableRot2RollerRot * Const.kSec2Talon100Ms * Const.kRot2TalonRaw));
        motor.configMotionAcceleration(2500);
    }

    public boolean getDeltaEdge() {
        return tableColorDetector.updateState();
    }

    public TableColor peekCurrentColor() {
        return tableColorDetector.getTableColor();
    }

    public void resetInitColorSensor(int direction) {
        tableColorDetector.init(direction);
    }

    public void resetEncoder(double resetTo) {
        motor.setSelectedSensorPosition((int) (resetTo * Const.kDeg2Rot * Const.kRot2TalonRaw));
    }

    public double getEncoderPosition() {
        return motor.getSelectedSensorPosition() * Const.kTalonRaw2Rot * Const.kRot2Deg;
    }

    public double getEncoderVelocity() {
        return motor.getSelectedSensorVelocity() * Const.kTalon100Ms2sec * Const.kTalonRaw2Rot * Const.kRot2Deg;
    }

    public void setSetpoint(double setpoint) {
        motor.set(ControlMode.MotionMagic, setpoint * Const.kDeg2Rot * Const.kRot2TalonRaw);
    }

    public void engageRoller(boolean engageRoller) {
        if (engageRoller) {
            piston.set(Value.kForward);
        } else {
            piston.set(Value.kReverse);
        }
    }

    public void stopMotor() {
        motor.set(ControlMode.PercentOutput, 0);
    }
}