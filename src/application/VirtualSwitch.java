package application;
import java.awt.Point;


//TODO: make sure this class description is accurate
/**
 *These objects respond to user input, and store the intended/desired state of a switch in the system
 *
 * @author Joanie Davis
 *
 */
public class VirtualSwitch {
	//Name of the switch
	private String label;
	
	//Label for the button indicating the true state of the switch
	private String trueStateLabel;
	
	//Label for the button indicating the false state of the switch
	private String falseStateLabel;
	
	//SwitchState indicating if the current state of the switch is TRUE, FALSE, or in some cases, THIRD (special case for 3-state switches)
	private SwitchState state;
	
	//boolean indicating if the switch has two or three states
	//TODO: both switches that have 3 states have a third state of Off, should that be a label or just assumed?
	private boolean hasThreeStates;
	
	public VirtualSwitch(String label, String trueStateLabel, String falseStateLabel, SwitchState state, boolean hasThreeStates) {
		this.label = label;
		this.trueStateLabel = trueStateLabel;
		this.falseStateLabel= falseStateLabel;
		this.state = state;
		this.hasThreeStates = hasThreeStates;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getTrueState() {
		return trueStateLabel;
	}
	
	public String getFalseState() {
		return falseStateLabel;
	}
	
	public SwitchState getState() {
		return state;
	}
	
	public boolean hasThreeStates(){
		return hasThreeStates;
	}
	
	public void setState(SwitchState newState){
		state = newState;
	}
}
