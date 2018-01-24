package robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.commands.JoystickCommand;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 *
 */
public class ChassisSubsystem extends Subsystem {

	DoubleSolenoid PistonOne = new DoubleSolenoid(0, 1);
	DoubleSolenoid PistonTwo = new DoubleSolenoid(2, 3);

	// Our talon speed controlers. Only uncomment when talons are connected:
	// TalonSRX leftMotor = new TalonSRX(1);
	// TalonSRX rightMotor = new TalonSRX(0);

	Victor elevatorMotor = new Victor(0);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new JoystickCommand());
		// leftMotor.setInverted(true);
	}

	public void setPistons(boolean state) {
		PistonOne.set(state ? Value.kForward : Value.kReverse);
		PistonTwo.set(state ? Value.kForward : Value.kReverse);
	}

	public void setElevatorSpeed(double speed) {
		elevatorMotor.set(speed);
	}

	/* 
	
	//These are the movement related methods
	
	public void setSpeed(double speed) {
		leftMotor.set(ControlMode.PercentOutput, speed);
		rightMotor.set(ControlMode.PercentOutput, speed);
	}

	public void setMotors(double rightSpeed, double leftSpeed) {
		leftMotor.set(ControlMode.PercentOutput, leftSpeed);
		rightMotor.set(ControlMode.PercentOutput, rightSpeed);
	}

	public void setMovement(double speed, double turn) {
		if (Math.abs(turn) < 0.2 && Math.abs(speed) > 0.1) {

			setMotors(speed, speed);

		} else if (Math.abs(turn) > 0.2 && Math.abs(speed) > 0.1) {

			if (turn > 0.2) {
				setMotors(speed, (1 - turn) * speed);
			} else {
				setMotors((1 - Math.abs(turn)) * speed, speed);
			}

		} else if (Math.abs(turn) > 0.2) {

			setMotors(-turn, turn);

		} else {
			setSpeed(0);
		}
	}
	
	*/
	
}
