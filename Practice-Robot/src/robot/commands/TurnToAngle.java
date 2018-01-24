package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import robot.Robot;
import robot.util.R_Gyro;

/**
 *
 */
public class TurnToAngle extends Command {

	R_Gyro gyro;
	double angle;
	double currentAngle;

	final double Min_Speed = 0.075;

	public TurnToAngle(double angle) {
		requires(Robot.chassisSubsystem);
		this.angle = angle;
		gyro = Robot.chassisSubsystem.gyro;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		gyro.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		currentAngle = gyro.getSplitAngle();

		double speed = ((Math.abs(angle) - Math.abs(currentAngle)) / 165);
		
		System.out.println(speed);
		if (currentAngle < angle) {
			// turning right
			// left forward, right backward
			Robot.chassisSubsystem.setMotors(-speed, speed);
		} else {
			Robot.chassisSubsystem.setMotors(speed, -speed);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		return timeSinceInitialized() >= 5 || ((currentAngle >= angle - 1 && currentAngle <= angle + 1));

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.chassisSubsystem.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
