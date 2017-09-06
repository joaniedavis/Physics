//package application.tests;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import application.VirtualSwitch;
//import application.SwitchState;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.control.Button;
//import javafx.scene.layout.Pane;
//
//public class VirtualSwitchMethodsTest {
//	private VirtualSwitch testSwitch;
//	private Button trueButton;
//	private Button falseButton;
//	private Button offButton;
//	
//	// Need to initialize JavaFX toolkit before any JavaFX elements are test
//	 @BeforeClass
//	 public static void initToolkit() {
//		 new JFXPanel(); // initializes JavaFX environment
//	 }
//	
//	@Before
//	public void run() {
//		trueButton = new Button("trueButton");
//		falseButton = new Button("falseButton");
//		offButton = new Button("offButton");
//		Pane pane = new Pane();
//		testSwitch = new VirtualSwitch(pane, trueButton, falseButton, offButton);
//	}
//	
//	@Test
//	public void testUpdateTrue() {
//		testSwitch.update(trueButton);
//		assert(testSwitch.getState().equals(SwitchState.TRUE));
//	}
//	
//	
//	@Test
//	public void testUpdateFalse() {
//		testSwitch.update(falseButton);
//		assert(testSwitch.getState().equals(SwitchState.FALSE));
//	}
//	
//	@Test
//	public void testUpdateOff() {
//		testSwitch.update(offButton);
//		assert(testSwitch.getState().equals(SwitchState.OFF));
//	}
//
//}
