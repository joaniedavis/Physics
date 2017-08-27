package application;

import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class VirtualSwitchHandler {
	// HashMap that has every single button as a key, each pointing to the VirtualSwitch that contains it
	private HashMap<Button, VirtualSwitch> buttonToSwitchMap;
	
	// HashMap that maps a string to every VirtualSwitch
	// This is used in addition to the buttonToSwitchMap, 
	// as that map will have duplicate VirtualSwitches in the values, whereas this one will not.
	// TODO: Check if this is absolutely needed or if it's useless duplication
	private HashMap<String, VirtualSwitch> virtualSwitchMap;
	
	public VirtualSwitchHandler() {
		this.buttonToSwitchMap = new HashMap<Button, VirtualSwitch>();
		this.virtualSwitchMap = new HashMap<String, VirtualSwitch>();
	}
	
	/**
	 * Creates a VirtualSwitch and adds it and its buttons to the buttonToSwitchMap
	 * @param name - a string for the name that will be mapped to the Switch
	 * @param pane - the Switch's Pane
	 * @param trueButton - the Switch's trueButton
	 * @param falseButton - the Switch's falseButton
	 * @param offButton - the Switch's (optional) offButton.  If there is no offButton, pass in null
	 */
	public void addVirtualSwitch(String name, Node pane, Node trueButton, Node falseButton, Node offButton) {
		// Create VirtualSwitch
		VirtualSwitch vs = new VirtualSwitch((Pane) pane, (Button) trueButton, (Button) falseButton, (Button) offButton);
		
		// Name Switch and add to virtualSwitchMap
		virtualSwitchMap.put(name, vs);
		
		// Add button relations to buttonToSwitchMap
		buttonToSwitchMap.put((Button) trueButton, vs);
		buttonToSwitchMap.put((Button) falseButton, vs);
		if(offButton != null) {
			buttonToSwitchMap.put((Button) offButton, vs);
		}
	}
	
	/**
	 * Retrieves the corresponding VirtualSwitch for the button, and updates it
	 * @param button - Button object that triggered the update process, used to determine VirtualSwitch
	 */
	public void updateVirtualSwitch(Button button) {
		VirtualSwitch vs = buttonToSwitchMap.get(button);
		if (vs != null) {
			vs.update(button);
		} else {
			throw new Error(String.format("Incorrect Button Name, %s, given", button.toString()));
		}
	}
	
	/**
	 * TODO: Is this necessary?
	 * @param name - the name of the VirtualSwitch to be retrieved
	 * @return the VirtualSwitch that corresponds to the name
	 */
	public VirtualSwitch getSwitch(String name) {
		return virtualSwitchMap.get(name);
	}
	
	
	// TODO: I'm not sure how I want to represent the switch states yet, so I'm leaving this blank for the moment.
	public void getSwitchStates() {
		System.out.println("Reading Virtual Switches");
		
	}
}
