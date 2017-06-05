package application;
/**
 * This class interfaces with the GPIO pins to physically change the state of the corresponding pin in the system.
 * @author Joanie Davis
 *
 */

public class RealRelay {
	//Name of the relay
	private String name;
	
	//Corresponding pin number
	private int pinNumber;
	
	//Status of the pin, indicating if it is on or off
	private RelayStatus status;
	
	public RealRelay(String name, int pinNumber, RelayStatus status) {
		this.name = name;
		this.pinNumber = pinNumber;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPinNumber() {
		return pinNumber;
	}
	
	public RelayStatus getStatus() {
		return status;
	}
}
