package robot.commands;

import OI.OI;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.ChassisSubsystem;

/**
 *
 */
public class JoystickCommand extends Command {

	ChassisSubsystem chassisSubsystem;
	OI oi;

	public JoystickCommand() {
		requires(Robot.chassisSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		chassisSubsystem = Robot.chassisSubsystem;
		oi = Robot.oi;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double elevatorSpeed = oi.getClimb();
		
		chassisSubsystem.setElevatorSpeed(elevatorSpeed);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
