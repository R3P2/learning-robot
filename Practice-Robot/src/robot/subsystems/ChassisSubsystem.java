package robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;
import robot.commands.JoystickCommand;
import robot.util.R_Gyro;
import robot.util.R_Victor;

public class ChassisSubsystem extends Subsystem {

	R_Victor rightMotor = new R_Victor(RobotMap.RIGHT_MOTOR_PORT, RobotMap.MotorSensitivity);
	R_Victor leftMotor = new R_Victor(RobotMap.LEFT_MOTOR_PORT, RobotMap.MotorSensitivity);

	Encoder leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_PORT_ONE, RobotMap.LEFT_ENCODER_PORT_TWO);
	Encoder rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_PORT_ONE, RobotMap.RIGHT_ENCODER_PORT_TWO, true);

	DoubleSolenoid PistonOne = new DoubleSolenoid(0, 1);
	DoubleSolenoid PistonTwo = new DoubleSolenoid(2, 3);

	public R_Gyro gyro = new R_Gyro ();
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new JoystickCommand());
	}

	public void setPistons(boolean state) {
		PistonOne.set(state ? Value.kForward : Value.kReverse);
		PistonTwo.set(state ? Value.kForward : Value.kReverse);
	}
	
	public void chassisInit() {

		leftMotor.setInverted(true);

	}
	
	public void teleOpInit() {
		
		resetEncoders();
		resetGyro();
		
	}

	public void invertMotors(boolean isInverted) {
		leftMotor.setInverted(isInverted);
		rightMotor.setInverted(!isInverted);
	}

	public void resetEncoders() {

		leftEncoder.reset();
		rightEncoder.reset();

	}
	
	public void resetGyro() {
		gyro.reset();
	}

	public void setMotors(double leftspeed, double rightspeed) {
		rightMotor.set(rightspeed * 0.5);
		leftMotor.set(leftspeed * 0.5);
	}
	
	public double getAverageRate(){
		return (Math.abs(rightEncoder.getRate()) + Math.abs(leftEncoder.getRate()))/2;
	}

	public double getAverageEncodersDistance () {
		
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
		
	}
	
	public void setSpeed(double speed) {

		setMotors(speed, speed);

	}

	public void setSpeed(double speed, double turn) {

		if (Math.abs(turn) < 0.2 && Math.abs(speed) > 0.1) {

			setMotors(speed, speed);

		} else if (Math.abs(turn) > 0.2 && Math.abs(speed) > 0.1) {
			
			if(turn > 0.2){
				setMotors(speed, (1 - turn) * speed);
			} else {
				setMotors((1 - Math.abs(turn)) * speed, speed);
			}
			
		} else if (Math.abs(turn) > 0.2) {

			setMotors(-turn, turn);

		} else {
			setMotors(0, 0);
		}
	}

	public void callSmartdashboard() {
		SmartDashboard.putData("Left Motor", leftMotor);
		SmartDashboard.putData("Right Motor", rightMotor);
		SmartDashboard.putData("Left Encoder", leftEncoder);
		SmartDashboard.putData("Right Encoder", rightEncoder);
		SmartDashboard.putData("Gyro", gyro);
		SmartDashboard.putNumber("Gyro Normalized Angle", gyro.getSplitAngle());
	}
}
