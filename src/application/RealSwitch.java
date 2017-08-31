package application;
/**
 * RealSwitches are in the real physical switches of the system
 * Connect to the 32 pin of the IO board
 *
 * Stores the data corresponding to a real switch in the physical system
 * State value is updated based on input from the physical system
 *
 * @author Joanie Davis
 *
 */
public class RealSwitch {
	//The name of the switch
	private String name;

	//the corresponding pin number
	//TODO: is this necessary?
	private int pinNumber;

	//the current state of the switch
	private SwitchState state;

	public RealSwitch(String name, int pinNumber, SwitchState state) {
		this.name = name;
		this.pinNumber = pinNumber;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public  SwitchState getState() {
		return state;
	}
}
