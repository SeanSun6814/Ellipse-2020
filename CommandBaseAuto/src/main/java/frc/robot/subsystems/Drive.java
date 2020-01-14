package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {

    private Spark leftMotor1;
    private Spark leftMotor2;
    private Spark rightMotor1;
    private Spark rightMotor2;

    public Drive() {
        leftMotor1 = new Spark(0);
        leftMotor2 = new Spark(1);
        rightMotor1 = new Spark(2);
        rightMotor2 = new Spark(3);
    }

    public void turnMotors(double left, double right) {
        leftMotor1.set(left);
        leftMotor2.set(left);
        rightMotor1.set(-right);
        rightMotor2.set(-right);
    }

    public void stopMotors() {
        turnMotors(0, 0);
    }

    @Override
    public void periodic() {
    }
}
