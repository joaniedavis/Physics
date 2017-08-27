package application.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import application.VirtualSwitch;
import application.VirtualSwitchHandler;
import application.SwitchState;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class VirtualSwitchHandlerTest {

	private static VirtualSwitchHandler handler;
	private static Button trueButton;
	private static Button falseButton;
	private static Button offButton;
	
	// Need to initialize JavaFX toolkit before any JavaFX elements are test
	@BeforeClass
	public static void initToolkit() {
		new JFXPanel(); // initializes JavaFX environment
		handler = new VirtualSwitchHandler();
		trueButton = new Button("trueButton");
		falseButton = new Button("falseButton");
		offButton = new Button("offButton");
		Pane pane = new Pane();
		handler.addVirtualSwitch("Test Switch", pane, trueButton, falseButton, offButton);
		
	}

	@Test
	public void testAddSwitch() {
		VirtualSwitch vSwitch = handler.getSwitch("Test Switch");
		assert(vSwitch.getState().equals(SwitchState.TRUE));
		assert(vSwitch.getTrueButton().equals(trueButton));
		assert(vSwitch.getFalseButton().equals(falseButton));
		assert(vSwitch.getOffButton().equals(offButton));
	}
	
	@Test
	public void testUpdateFalse() {
		handler.updateVirtualSwitch(falseButton);
		VirtualSwitch vSwitch = handler.getSwitch("Test Switch");
		
		assert(vSwitch.getState().equals(SwitchState.FALSE));
	}
	
	@Test
	public void testUpdateOff() {
		handler.updateVirtualSwitch(offButton);
		VirtualSwitch vSwitch = handler.getSwitch("Test Switch");
		
		assert(vSwitch.getState().equals(SwitchState.OFF));
	}
	
	@Test
	public void testUpdateTrue() {
		handler.updateVirtualSwitch(trueButton);
		VirtualSwitch vSwitch = handler.getSwitch("Test Switch");
		
		assert(vSwitch.getState().equals(SwitchState.TRUE));
	}
	
	
	@Test(expected = Error.class)
	public void testUpdateWrongSwitchName() {
		handler.updateVirtualSwitch(new Button("badButton"));
		VirtualSwitch vSwitch = handler.getSwitch("Test Switch");
	}

}
