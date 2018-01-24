package robot.util;

import edu.wpi.first.wpilibj.Victor;
import robot.RobotMap;

public class R_Victor extends Victor{

	double sensitivity = 0.8;
	double kp = 0.1;
	
	public R_Victor(int channel) {
		super(channel);
	}

	public R_Victor(int channel, double sensitivity) {
		super(channel);
		this.sensitivity = sensitivity;
	}
	
	@Override
	public void set(double speed){
		super.set(speed * sensitivity);
	}
	
	public void setPID(double setPoint, double feedback) {
		double normalizedFeedback = feedback/RobotMap.MAX_ENCODER_SPEED;
		
		if (normalizedFeedback >  1.0) { normalizedFeedback =  1.0; }
		if (normalizedFeedback < -1.0) { normalizedFeedback = -1.0; }

		double error = setPoint - normalizedFeedback;
		
		double output = kp * error;
		
		double normalizedOutput = output + setPoint;

		if (normalizedOutput >  1.0) { normalizedOutput =  1.0; }
		if (normalizedOutput < -1.0) { normalizedOutput = -1.0; }

		set(normalizedOutput);
	}
	
	
}