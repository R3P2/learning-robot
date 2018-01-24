package robot.commands;

import java.security.SecurityPermission;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import robot.Robot;

public class JoystickCommand extends Command {

	public JoystickCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassisSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
 
		double speed = Robot.oi.getSpeed();
		double turn = Robot.oi.getTurn();
		boolean isPistons = Robot.oi.isPistons();
		boolean isAutoTest = Robot.oi.isAutoTest();
		
		if (isAutoTest){
			
			Scheduler.getInstance().add(new DriveStraightWithGyro(3));
			
		}
		
		Robot.chassisSubsystem.setPistons(isPistons);
		Robot.chassisSubsystem.setSpeed(speed, turn);			
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
