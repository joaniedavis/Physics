package application;
import java.awt.Point;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


//TODO: make sure this class description is accurate
/**
 *These objects respond to user input, and store the intended/desired state of a switch in the system
 *
 * @author Joanie Davis
 *
 */
public class VirtualSwitch {
	// The JavaFX pane that contains the buttons and label of the Switch
	private Pane pane;
	
	// The button corresponding to the true state of the switch
	private Button trueButton;
	
	// The button corresponding to the false state of the switch
	private Button falseButton;
	
	// The optional third button corresponding to the off state of the switch
	private Button offButton;
	
	//SwitchState indicating if the current state of the switch is TRUE, FALSE, or in some cases, THIRD (special case for 3-state switches)
	private SwitchState state;
	
	public VirtualSwitch(Pane pane, Button trueButton, Button falseButton, Button offButton) {
		this.pane = pane;
		this.trueButton = trueButton;
		this.falseButton = falseButton;
		this.offButton = offButton;
		// Default to true
		this.state = SwitchState.TRUE;
	}
	
	// Takes in a Button and updates the Buttons and Switch accordingly
	// Only updates offButton if not null
	public void update(Button updateButton) {
		if (updateButton.equals(trueButton)) {
			trueButton.setDisable(true);
			falseButton.setDisable(false);
			if (offButton != null) {
				offButton.setDisable(false);
			}
			state = SwitchState.TRUE;
		} else if(updateButton.equals(falseButton)) {
			trueButton.setDisable(false);
			falseButton.setDisable(true);
			if (offButton != null) {
				offButton.setDisable(false);
			}
			state = SwitchState.FALSE;
		} else if(updateButton.equals(offButton) && updateButton != null) {
			trueButton.setDisable(false);
			falseButton.setDisable(false);
			if (offButton != null) {
				offButton.setDisable(true);
			} else {
				System.out.println("We should never get here");
			}
			state = SwitchState.OFF;
		} else {
			// TODO: Handle this better than just printing that there was a problem
			System.out.println("We should never get here");
		}
	}
	
	public SwitchState getState() {
		return state;
	}
	
	public Button getTrueButton() {
		return trueButton;
	}
	
	public Button getFalseButton() {
		return falseButton;
	}
	
	public Button getOffButton() {
		return offButton;
	}
}
