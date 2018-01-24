package robot.OI;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	GameController gameController = new XBoxController(0);

	public double getSpeed() {
		return gameController.getLeftYAxis();
	}

	public double getTurn() {

		return gameController.getRightXAxis();

	}
	
	public boolean isAutoTest () {
		
		return gameController.isXDown();
		
	}
	
	public boolean isPistons(){
		return gameController.isADown();
	}

}
