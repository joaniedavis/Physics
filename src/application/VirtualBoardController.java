package application;

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

public class VirtualBoardController implements Initializable {
	// All of these accesible features have to be named here in order to retrieve them based on their FX-IDs
	@FXML
	private Button Start_Button;

	@FXML
	public Rectangle HeartBeat_LED0_LED;
	private int heartbeatCount;

	@FXML
	private Rectangle ValveEnable_LED2_LED;
	@FXML
	private Rectangle Interlock_LED3_LED_LED;
	@FXML
	private Rectangle BLValve_LED5_LED;
	@FXML
	private Rectangle ChamHighVac_LED6_LED;
	@FXML
	private Rectangle TC2_LED7_LED;
	@FXML
	private Rectangle PumpValve_LED8_LED;
	@FXML
	private Rectangle TurboON_LED9_LED;
	@FXML
	private Rectangle LockAtm_LED10_LED;
	@FXML
	private Rectangle LockValve_LED12_LED;
	@FXML
	private Rectangle TC1_LED14_LED;
	@FXML
	private Rectangle TurboRoughValve_LED15_LED;
	@FXML
	private Rectangle VentValve_LED16_LED;
	@FXML
	private Rectangle LockRoughValve_LED17_LED;
	@FXML
	private Rectangle VentLockCham_LED18_LED;
	@FXML
	private Rectangle VentLock_LED19_LED;
	@FXML
	private Rectangle LockChamPumping_LED20_LED;

	//private HashMap<String, Pane> vboxes;
	private VirtualSwitchHandler vsHandler;
	private HashMap<String, Boolean> conditionMap;
	private ArrayList<VirtualRelay> virtualRelays;

	// Single VirtualRelay declared for testing purposes.
	private VirtualRelay ValveEnable_VR1;
	private LED ValveEnable_LED2;

	// leds is a mapping of an LED object to a corresponding Rectangle on the
	// GUI
	private HashMap<LED, Rectangle> leds;

	private ArrayList<LED> ledList;

	// -- IO Elements

	// Reader to read the gpio pins
	// GpioReader gpioReader;

	private PythonInterpreter interpreter;

	/**
	 * This method initializes the collections that will be used throughout the
	 * processes of the applciation.
	 * 
	 * vboxes is a HashMap linking String names to VBox objects from the view.
	 * It is used in relation to the virtual switches. leds is a HashMap that
	 * maps an LED object to a Rectangle from the view ledList is an arrayList
	 * of LED objects TODO: clean up implementation of the leds, there shouldn't
	 * be any reason for them to be in multiple collections.
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Make IO Elements

		// Set up Python interpreter
		PythonInterpreter.initialize(System.getProperties(), System.getProperties(), new String[0]);
		this.interpreter = new PythonInterpreter();

		// GpioReader gpioReader = new GpioReader();
		

		vsHandler = new VirtualSwitchHandler();
		leds = new HashMap<LED, Rectangle>();
		conditionMap = new HashMap<String, Boolean>();
		virtualRelays = new ArrayList<VirtualRelay>();

		heartbeatCount = 0;

	}
	
	private void setup(Scene scene) {
		fillVSHandler(scene);
		InitializeLEDs();
		initializeConditionMap();
		initializeVirtualRelays();

	}
	
	private void fillVSHandler(Scene scene) {
		if(scene == null) {
			System.out.println("Null Scene!");
			return;
		}
		
		Node vbox = scene.lookup("#ValveEnable_S2_VBox");
		Node tnode = scene.lookup("#ValveEnable_S2_True");
		Node fnode = scene.lookup("#ValveEnable_S2_False");
		
		vsHandler.addVirtualSwitch("ValveEnable_S2", vbox, tnode, fnode, null);
		vsHandler.addVirtualSwitch("TurboNLK_m_S3", scene.lookup("#TurboNLK_m_S3_VBox"), scene.lookup("#TurboNLK_m_S3_True"), scene.lookup("#TurboNLK_m_S3_False"), null);
		vsHandler.addVirtualSwitch("BLV_AutoClose_S7", scene.lookup("#BLV_AutoClose_S7_VBox"), scene.lookup("#BLV_AutoClose_S7_True"), scene.lookup("#BLV_AutoClose_S7_False"), null);
		vsHandler.addVirtualSwitch("PV_AutoClose_S7", scene.lookup("#PV_AutoClose_S7_VBox"), scene.lookup("#PV_AutoClose_S7_True"), scene.lookup("#PV_AutoClose_S7_False"), null);
		vsHandler.addVirtualSwitch("LockValve_AutoClose_S8", scene.lookup("#LockValve_AutoClose_S8_VBox"), scene.lookup("#LockValve_AutoClose_S8_True"), scene.lookup("#LockValve_AutoClose_S8_False"), null);
		vsHandler.addVirtualSwitch("LockRoughingValve_AutoClose_S9", scene.lookup("#LockRoughingValve_AutoClose_S9_VBox"), scene.lookup("#LockRoughingValve_AutoClose_S9_True"), scene.lookup("#LockRoughingValve_AutoClose_S9_False"), null);
		vsHandler.addVirtualSwitch("TurboRoughValve_AutoClose_S10", scene.lookup("#TurboRoughValve_AutoClose_S10_VBox"), scene.lookup("#TurboRoughValve_AutoClose_S10_True"), scene.lookup("#TurboRoughValve_AutoClose_S10_False"), null);
		vsHandler.addVirtualSwitch("VentValve_AutoClose_S11", scene.lookup("#VentValve_AutoClose_S11_VBox"), scene.lookup("#VentValve_AutoClose_S11_True"), scene.lookup("#VentValve_AutoClose_S11_False"), null);
		vsHandler.addVirtualSwitch("VentLockChamber_m_S12", scene.lookup("#VentLockChamber_m_S12_VBox"), scene.lookup("#VentLockChamber_m_S12_True"), scene.lookup("#VentLockChamber_m_S12_False"), scene.lookup("#VentLockChamber_m_S12_Off"));
		vsHandler.addVirtualSwitch("VentLock_m_S11", scene.lookup("#VentLock_m_S11_VBox"), scene.lookup("#VentLock_m_S11_True"), scene.lookup("#VentLock_m_S11_False"), scene.lookup("#VentLock_m_S11_Off"));
		vsHandler.addVirtualSwitch("PumpLockChamber_m_S14", scene.lookup("#PumpLockChamber_m_S14_VBox"), scene.lookup("#PumpLockChamber_m_S14_True"), scene.lookup("#PumpLockChamber_m_S14_False"), null);
	}

	/**
	 * This method creates the ConditionMap, giving each condition a default
	 * value
	 */
	private void initializeConditionMap() {
		conditionMap.put("Test24V_Denkovi0_19", false);

		conditionMap.put("ValveEnable_VR1", false);

		conditionMap.put("TurboNLK_VR104", false);

		conditionMap.put("BLV_open_J1_0", true);
		conditionMap.put("BLV_closed_J1_1", false);

		conditionMap.put("IGltSP_VR103", true);
		conditionMap.put("!IGltSP_VR103", false);

		conditionMap.put("TC2ltSP_VR203", true);
		conditionMap.put("!TC2ltSP_VR203", false);

		conditionMap.put("PV_open_J2_2", true);
		conditionMap.put("PV_closed_J2_3", false);

		conditionMap.put("TurboatSpeed_VR202", true);

		conditionMap.put("!TC3ltSP2_VR102", true);

		conditionMap.put("TC3ltSP1_VR301", true);
		conditionMap.put("!TC3ltSP1_VR301", false);

		conditionMap.put("LVopen_VR501", true);
		conditionMap.put("LVclosed_VR502", false);

		conditionMap.put("TC1ltSP_VR201", true);
		conditionMap.put("!TC1ltSP_VR201", false);

		conditionMap.put("TurboRoughopen_VR601", true);
		conditionMap.put("TurboRoughclosed_VR602", false);

		conditionMap.put("LOGIC_VentValveopen_VR303", true);
		conditionMap.put("!LOGIC_VentValveopen_VR303", false);

		conditionMap.put("LockRoughopen_VR503", true);
		conditionMap.put("LockRoughcloses_VR504", false);

		conditionMap.put("VendLockCham_m_S12", false);

		conditionMap.put("VentLock_m_S13", false);

		conditionMap.put("PumpLockChamber_m_S14", true);
		conditionMap.put("", null);

	}

	/**
	 * This method creates the ConditionMap, giving each condition a default
	 * value
	 */
	private void initializeVirtualRelays() {
		VirtualRelay Test24V_Denkovi0_19 = new VirtualRelay("Test24V_Denkovi0_19", false);

		ValveEnable_VR1 = new VirtualRelay("ValveEnable_VR1", false);

		VirtualRelay TurboNLK_VR104 = new VirtualRelay("TurboNLK_VR104", false);

		// conditionMap.put("BLV_open_J1_0", true);
		// conditionMap.put("BLV_closed_J1_1", false);
		//
		// conditionMap.put("IGltSP_VR103", true);
		// conditionMap.put("!IGltSP_VR103", false);
		//
		// conditionMap.put("TC2ltSP_VR203", true);
		// conditionMap.put("!TC2ltSP_VR203", false);
		//
		// conditionMap.put("PV_open_J2_2", true);
		// conditionMap.put("PV_closed_J2_3", false);
		//
		// conditionMap.put("TurboatSpeed_VR202", true);
		//
		// conditionMap.put("!TC3ltSP2_VR102", true);
		//
		// conditionMap.put("TC3ltSP1_VR301", true);
		// conditionMap.put("!TC3ltSP1_VR301", false);
		//
		// conditionMap.put("LVopen_VR501", true);
		// conditionMap.put("LVclosed_VR502", false);
		//
		// conditionMap.put("TC1ltSP_VR201", true);
		// conditionMap.put("!TC1ltSP_VR201", false);
		//
		// conditionMap.put("TurboRoughopen_VR601", true);
		// conditionMap.put("TurboRoughclosed_VR602", false);
		//
		// conditionMap.put("LOGIC_VentValveopen_VR303", true);
		// conditionMap.put("!LOGIC_VentValveopen_VR303", false);
		//
		// conditionMap.put("LockRoughopen_VR503", true);
		// conditionMap.put("LockRoughcloses_VR504", false);
		//
		// conditionMap.put("VendLockCham_m_S12", false);
		//
		// conditionMap.put("VentLock_m_S13", false);
		//
		// conditionMap.put("PumpLockChamber_m_S14", true);
		// conditionMap.put("", null);

	}

	/**
	 * This method initializes the leds HashMap
	 */
	private void InitializeLEDs() {
		// initialize the LEDs
		// TODO: This one might have to be a special case, as it'll have more
		// than 3 colors
		// LED HeartBeat_LED0 = new LED("Program Running", Color.RED,
		// Color.GREEN, Color.PINK, "", "", false);

		// TODO: Figure out if it's a problem to have one condition and an else
		// clause instead of 2
		// TODO-2: we'll probably need to make some if/else check to make sure
		// cond2 isn't "", that should work
		LED Test24V_LED1 = new LED("24V on", Color.GREEN, null, Color.GRAY, "Test24V_Denkovi0_19", "", true);
		ValveEnable_LED2 = new LED("Valves Enabled", Color.YELLOW, null, Color.GRAY, "ValveEnable_VR1", "", true);
		LED Interlock_LED3 = new LED("Bypass", Color.RED, null, Color.GRAY, "TurboNLK_VR104", "", true);
		LED BLValve_LED5 = new LED("Beam Line Valve", Color.GREEN, Color.RED, Color.GRAY, "BLV_open_J1_0",
				"BLV_closed_J1_1", true);
		LED ChamHighVac_LED6 = new LED("Chamber at High Vacuum", Color.GREEN, Color.RED, null, "IGltSP_VR103",
				"!IGltSP_VR103", false);
		LED TC2_LED7 = new LED("Chamber at Rough Vacuum", Color.GREEN, Color.RED, null, "TC2ltSP_VR203",
				"!TC2ltSP_VR203", false);
		LED PumpValve_LED8 = new LED("Pump Valve", Color.GREEN, Color.RED, Color.GRAY, "PV_open_J2_2", "PV_closed_J2_3",
				true);
		LED TurboON_LED9 = new LED("Turbo at Speed", Color.YELLOW, null, Color.GRAY, "TurboatSpeed_VR202", "", true);
		LED LockAtm_LED10 = new LED("Lock Vented", Color.YELLOW, null, Color.GRAY, "!TC3ltSP2_VR102", "", true);
		LED TC3_LED11 = new LED("Lock at Vacuum", Color.GREEN, Color.RED, null, "TC3ltSP1_VR301", "!TC3ltSP1_VR301",
				false);
		LED LockValve_LED12 = new LED("Lock Valve", Color.GREEN, Color.RED, Color.GRAY, "LVopen_VR501",
				"LVclosed_VR502", true);
		LED TC1_LED14 = new LED("Turbo Backing", Color.GREEN, Color.RED, null, "TC1ltSP_VR201", "!TC1ltSP_VR201",
				false);
		// TODO: Color for fault? Currently it's black
		LED TurboRoughValve_LED15 = new LED("Turbo Rough Valve", Color.GREEN, Color.RED, Color.BLACK,
				"TurboRoughopen_VR601", "TurboRoughclosed_VR602", true);
		LED VentValve_LED16 = new LED("Vent Valve", Color.GREEN, Color.RED, null, "LOGIC_VentValveopen_VR303",
				"!LOGIC_VentValveopen_VR303", false);
		// TODO-2 also fault state is black here
		LED LockRoughValve_LED17 = new LED("Lock Rough Valve", Color.GREEN, Color.RED, Color.BLACK,
				"LockRoughopen_VR503", "LockRoughcloses_VR504", true);
		LED VentLockCham_LED18 = new LED("Venting Lock and Chamber", Color.YELLOW, null, Color.GRAY,
				"VendLockCham_m_S12", "", true);
		LED VentLock_LED19 = new LED("Venting Lock", Color.YELLOW, null, Color.GRAY, "VentLock_m_S13", "", true);
		// TODO: Ask DeYoung if this one needs a label
		LED LockChamPumping_LED20 = new LED("", Color.YELLOW, null, Color.GRAY, "PumpLockChamber_m_S14", "", true);

		// Add LEDS to leds Array
		// leds.add(,Test24V_LED1);
		leds.put(ValveEnable_LED2, ValveEnable_LED2_LED);
		leds.put(Interlock_LED3, Interlock_LED3_LED_LED);
		leds.put(BLValve_LED5, BLValve_LED5_LED);
		leds.put(ChamHighVac_LED6, ChamHighVac_LED6_LED);
		leds.put(TC2_LED7, TC2_LED7_LED);
		leds.put(PumpValve_LED8, PumpValve_LED8_LED);
		leds.put(TurboON_LED9, TurboON_LED9_LED);
		leds.put(LockAtm_LED10, LockAtm_LED10_LED);
		// leds.put(TC3_LED11_LED, TC3_LED11);
		leds.put(LockValve_LED12, LockValve_LED12_LED);
		leds.put(TC1_LED14, TC1_LED14_LED);
		leds.put(TurboRoughValve_LED15, TurboRoughValve_LED15_LED);
		leds.put(VentValve_LED16, VentValve_LED16_LED);
		leds.put(LockRoughValve_LED17, LockRoughValve_LED17_LED);
		leds.put(VentLockCham_LED18, VentLockCham_LED18_LED);
		leds.put(VentLock_LED19, VentLock_LED19_LED);
		leds.put(LockChamPumping_LED20, LockChamPumping_LED20_LED);

		// // Add LEDS to leds Array
		// ledList.add(Test24V_LED1);
		// ledList.add(ValveEnable_LED2);
		// ledList.add(Interlock_LED3);
		// ledList.add(BLValve_LED5);
		// ledList.add(ChamHighVac_LED6);
		// ledList.add(TC2_LED7);
		// ledList.add(PumpValve_LED8);
		// ledList.add(TurboON_LED9);
		// ledList.add(LockAtm_LED10);
		// ledList.add(TC3_LED11);
		// ledList.add(TC1_LED14);
		// ledList.add(TurboRoughValve_LED15);
		// ledList.add(VentValve_LED16);
		// ledList.add(LockRoughValve_LED17);
		// ledList.add(VentLockCham_LED18);
		// ledList.add(VentLock_LED19);
		// ledList.add(LockChamPumping_LED20);

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

		// TODO: This section is commented out as I'm developing
		// on my Windows Machine, which has no GPIO board to read
		// try {
		//// int[] states = gpioReader.read();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (UnsupportedBusNumberException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

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

//	private void readVirtualSwitch(Pane box, String boxName) {
//		for (Node nodeIn : box.getChildren()) {
//			if (nodeIn instanceof Button) {
//				Button button = (Button) nodeIn;
//				if (!button.isDisabled()) {
//					if (button.getId().contains("_True")) {
//						// conditionMap.put("BLV_open_J1_0", true);
//						// conditionMap.put("BLV_closed_J1_1", false);
//						ValveEnable_VR1.setStatus(true);
//					} else if (button.getId().contains("_False")) {
//						// conditionMap.put("BLV_open_J1_0", false);
//						// conditionMap.put("BLV_closed_J1_1", true);
//						ValveEnable_VR1.setStatus(false);
//					}
//				}
//			}
//		}
//	}

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
		for (LED led : leds.keySet()) {
			// LED led = ledList.get(3);
			if (led != null) {
				// check cond1
				if (conditionMap.get(led.getCond1()) == true) {
					led.setCurrentColor(led.getCond1Color());
					System.out.println("Color 1!");
					// else check cond2
				} else if (led.getCond2Color() != null) {
					if (conditionMap.get(led.getCond2()) == true) {
						led.setCurrentColor(led.getCond2Color());
						System.out.println("Color 2!");
						// else check else
					} else if (led.hasElseClause() == true) {
						led.setCurrentColor(led.getElseColor());
						System.out.println("Color ELSE!");
						// finally panic()
					} else {
						System.out.println("WE HAVE A WEIRD COLOR???");
					}
				}
				Rectangle vled = leds.get(led);
				if (vled != null) {
					vled.setFill(led.getCurrentColor());
				}
			}
		}

		if (ValveEnable_VR1.getStatus() == true) {
			ValveEnable_LED2.setCurrentColor(ValveEnable_LED2.getCond1Color());
		} else {
			ValveEnable_LED2.setCurrentColor(ValveEnable_LED2.getCond2Color());
		}

	}
}
