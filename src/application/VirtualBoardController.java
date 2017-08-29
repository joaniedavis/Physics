package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

//Python stuff that may be deleted later
import org.python.core.PyInstance;
import org.python.util.PythonInterpreter;

import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

public class VirtualBoardController implements Initializable {
	// All of these accesible features have to be named here in order to retrieve them based on their FX-IDs
	@FXML
	private Button Start_Button;

	@FXML
	public Rectangle HeartBeat_LED0_LED;
	private int heartbeatCount;

	
	@FXML
	private Rectangle LockRoughValve_LED17_LED;
	
	//private HashMap<String, Pane> vboxes;
	private VirtualSwitchHandler vsHandler;
	private LEDHandler ledHandler;
	private HashMap<String, Boolean> virtualRelays;
	private HashMap<String, Boolean> realRelays;
	private HashMap<String, Boolean> momentarySwitches;
	private HashMap<String, Boolean> allRelays;
//	private ArrayList<VirtualRelay> virtualRelays;

	// Single VirtualRelay declared for testing purposes.
	private VirtualRelay ValveEnable_VR1;
	private LED ValveEnable_LED2;

	// leds is a mapping of an LED object to a corresponding Rectangle on the
	// GUI
	// private HashMap<LED, Rectangle> leds;

	// private ArrayList<LED> ledList;

	// -- IO Elements

	// Reader to read the gpio pins
	 GpioReader gpioReader;
	 DenkoviRelayWriter dRWriter;
	 WaveShareRelayWriter wsRWriter;

	private PythonInterpreter interpreter;

	/**
	 * This method initializes the collections that will be used throughout the
	 * processes of the applciation.
	 *
	 * vboxes is a HashMap linking String names to VBox objects from the view.
	 * It is used in relation to the virtual switches. leds is a HashMap that
	 * maps an LED object to a Rectangle from the view ledList is an arrayList
	 * of LED objects
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Make IO Elements

		// Set up Python interpreter
		PythonInterpreter.initialize(System.getProperties(), System.getProperties(), new String[0]);
		this.interpreter = new PythonInterpreter();

		 try {
			gpioReader = new GpioReader();
		} catch (UnsupportedBusNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 dRWriter = new DenkoviRelayWriter();
		 wsRWriter = new WaveShareRelayWriter();


		vsHandler = new VirtualSwitchHandler();
		ledHandler = new LEDHandler();
		virtualRelays = new HashMap<String, Boolean>();
		realRelays = new HashMap<String, Boolean>();
		momentarySwitches = new HashMap<String, Boolean>();
		allRelays = new HashMap<String, Boolean>();
		
		

		heartbeatCount = 0;

	}

	private void setup(Scene scene) {
		fillVSHandler(scene);
		fillLEDHandler(scene);
		initializeVirtualRelays();
		initializeRealRelays();
		initializeMomentarySwitches();
		updateAllRelays();
	}

	private void fillVSHandler(Scene scene) {
		if(scene == null) {
			System.out.println("Null Scene!");
			return;
		}

		vsHandler.addVirtualSwitch("LockRoughingValve_AutoClose_S9", scene.lookup("#LockRoughingValve_AutoClose_S9_VBox"), scene.lookup("#LockRoughingValve_AutoClose_S9_True"), scene.lookup("#LockRoughingValve_AutoClose_S9_False"), null);
	}

	private void fillLEDHandler(Scene scene) {
		if(scene == null) {
			System.out.println("Null Scene!");
			return;
		}

		ledHandler.addLED("LockRoughValve_LED17", Color.GREEN, Color.RED, Color.GRAY, "LockRoughopen_VR503", "LockRoughclosed_VR504", true, (Rectangle) scene.lookup("#LockRoughValve_LED17_LED"));
		
	}


	/**
	 * This method creates the HashMap of the virtualRelays, giving each condition a default
	 * value
	 */
	private void initializeVirtualRelays() {
		virtualRelays.put("LockRoughopen_VR503", true);
		virtualRelays.put("LockRoughclosed_VR504", true);
	}
	
	/**
	 * This method creates the HashMap of the realRelays, giving each condition a default
	 * value
	 */
	private void initializeRealRelays() {
		realRelays.put("RVlock_open_J5_8", true);
		realRelays.put("RVlock_closed_J5_9", true);
	}
	
	/**
	 * This method creates the HashMap of the momentarySwitches
	 */
	private void initializeMomentarySwitches() {
		
	}

	
	/**
	 * This method updates the allRelays HashMap to contain the current values of both the real and virtual Relays
	 */
	
	private void updateAllRelays() {
		allRelays.clear();
		allRelays.putAll(virtualRelays);
		allRelays.putAll(realRelays);
		allRelays.putAll(momentarySwitches);
		allRelays.put("", null);
	}
	

	// ---------Button Methods----------------------

	/**
	 * If a button is pressed that is not disabled, update the VBox
	 */
	@FXML
	private void buttonPressed(ActionEvent event) {
		Button button = (Button) event.getSource();
		vsHandler.updateVirtualSwitch(button);
	}


	// ------------Main Controller Methods

	/**
	 * Create a timeline with frames for each of the updating functions and
	 * cycle through those frames infinitely
	 */
	@FXML
	private void startApp(ActionEvent event) {
		Scene scene = Start_Button.getScene();
		setup(scene);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				readGPIO();
			}
		}));
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				readVirtualSwitches();
			}
		}));
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				updateVirtualRelays();
			}
		}));
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				updateRealRelays();
			}
		}));
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				updateLEDs();
			}
		}));
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				incrementHeartbeatColor();
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.playFromStart();

	}

	// ---------Heartbeat Methods-------------------

	private void incrementHeartbeatColor() {
		switch (heartbeatCount) {
		case 0:
			HeartBeat_LED0_LED.setFill(Color.RED);
			heartbeatCount++;
			break;
		case 1:
			HeartBeat_LED0_LED.setFill(Color.ORANGE);
			heartbeatCount++;
			break;
		case 2:
			HeartBeat_LED0_LED.setFill(Color.YELLOW);
			heartbeatCount++;
			break;
		case 3:
			HeartBeat_LED0_LED.setFill(Color.GREEN);
			heartbeatCount++;
			break;
		case 4:
			HeartBeat_LED0_LED.setFill(Color.BLUE);
			heartbeatCount++;
			break;
		case 5:
			HeartBeat_LED0_LED.setFill(Color.PURPLE);
			heartbeatCount = 0;
			break;

		}
	}

	/**
	 * Reads in values from the GPIO board using a Python call
	 */
	private void readGPIO() {
		System.out.println("Reading GPIO");
		int[] pinStatus = gpioReader.getGPIOStatus();
		
		int RVlock_open_J5_8 = pinStatus[18];
		if (RVlock_open_J5_8 == 1) {
			realRelays.put("RVlock_open_J5_8", true);
		} else {
			realRelays.put("RVlock_open_J5_8", true);
		}
		
		int RVlock_closed_J5_9 = pinStatus[19];
		if (RVlock_closed_J5_9 == 1) {
			realRelays.put("RVlock_closed_J5_9", true);
		} else {
			realRelays.put("RVlock_closed_J5_9", true);
		}
		
	}

	// Helper Jython Functions---------
	void execfile(final String fileName) {
		this.interpreter.execfile(fileName);
	}

	PyInstance createClass(final String className, final String opts) {
		return (PyInstance) this.interpreter.eval(className + "(" + opts + ")");
	}
	// -------------------------------

	// TODO: Possibly rename this to updatingConditions,
	// Because that's what it does right now.
	/**
	 * Iterates through VBoxes' buttons and updates ConditionMap based on those
	 * values TODO: Not fully implemented - Currently only reads in from BLV
	 * VBox
	 */
	private void readVirtualSwitches() {
		vsHandler.getSwitchStates();
	}


	/**
	 * Updates Virtual Relays TODO: Not implemented
	 */
	private void updateVirtualRelays() {
		System.out.print("Updating Virual Relays");
	}

	/**
	 * Reads in values from the Real relays and stores them internally
	 */
	// TODO: Is this where the conditions would be updated?
	private void updateRealRelays() {
		System.out.println("Updating Real Relays");
	}

	/**
	 * Iterates through all of the LED objects in the ledList. For each LED
	 * object, checks against the LED's conditions for a true condition. Updates
	 * the LED's associated Rectangle object's color in the view to match the
	 * true condition.
	 */
	private void updateLEDs() {
		System.out.println("Updating LEDS");
		ledHandler.updateLEDs(allRelays);

	}
}
