package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;
import robot.util.R_Gyro;

/**
 *
 */
public class DriveStraightWithGyro extends Command {

	R_Gyro gyro;
	
	double distance;
	double speed;

	/**
	 * 
	 * @param speed The speed from -1 to 1
	 * @param distance The distance in feet
	 */
    public DriveStraightWithGyro(double distance) {
        requires(Robot.chassisSubsystem);
        
        this.gyro = Robot.chassisSubsystem.gyro;
        this.distance = distance * RobotMap.MAX_ENCODER_COUNTS_PER_FT;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gyro.reset();
    	speed = -0.5;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double rightSpeed = speed;
    	double leftSpeed = speed;
    	
    	double angle = gyro.getSplitAngle();
    	if(angle > 2){
    		rightSpeed = speed * 0.25;
    		leftSpeed = -speed * 0.25;
    	}else if (angle < -2){
    		rightSpeed = -speed * 0.25;
    		leftSpeed = speed * 0.25;
    	}
    	
    	Robot.chassisSubsystem.setMotors(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	    	
        return Robot.chassisSubsystem.getAverageEncodersDistance() >= distance || timeSinceInitialized() >= 5.0;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
