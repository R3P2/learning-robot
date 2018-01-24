package robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	
	//Pulse Width Modulation Ports (PWM)
	
	public final static int LEFT_MOTOR_PORT = 0;
	public final static int RIGHT_MOTOR_PORT = 1;
	
	// Digital Input/Output Ports (DIO)
	
	public final static int LEFT_ENCODER_PORT_ONE = 0;
	public final static int LEFT_ENCODER_PORT_TWO = 1;
	public final static int RIGHT_ENCODER_PORT_ONE = 2;
	public final static int RIGHT_ENCODER_PORT_TWO = 3;
	
	
	// Physical Properties
	
	public final static int MAX_ENCODER_COUNTS_PER_FT = 300;
	public final static int MAX_ENCODER_SPEED = 1800;
	
	public static double MotorSensitivity = 0.75;
}
