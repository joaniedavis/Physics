package application;

//TODO: make sure this class description is accurate
/**
 * This class is similar to a RealRelay, but it is used to model a relay that is closed when a RealSwitch closes,
 * Completing part of a logic circuit.
 * 
 * This is included to make the translation from the schematic to this system easier
 * @author Joanie Davis
 *
 */

public class VirtualRelay {
	
	//Relay's name
	private String name;
	
	//indicates current status of the relay
	private RelayStatus status;
	
	public VirtualRelay(String name, RelayStatus status) {
		this.name = name;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	
	public RelayStatus getStatus() {
		return status;
	}
}
