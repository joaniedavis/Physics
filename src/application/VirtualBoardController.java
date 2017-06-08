package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class VirtualBoardController implements Initializable {
	@FXML
	private VBox ValveEnable_S2_VBox;
	@FXML
	private VBox TurboNLK_m_S3_VBox;
	@FXML
	private VBox BLV_AutoClose_S7_VBox;
	@FXML
	private VBox PV_AutoClose_S7_VBox;
	@FXML
	private VBox LockValve_AutoClose_S8_VBox;
	@FXML
	private VBox LockRoughingValve_AutoClose_S9_VBox;
	@FXML
	private VBox TurboRoughValve_AutoClose_S10_VBox;
	@FXML
	private VBox VentValve_AutoClose_S11_VBox;
	@FXML
	private VBox VentLockChamber_m_S12_VBox;
	@FXML
	private VBox VentLock_m_S11_VBox;
	@FXML
	private VBox PumpLockChamber_m_S14_VBox;

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

	private HashMap<String, VBox> vboxes;
	private HashMap<LED, Rectangle> leds;
	
	private VirtualBoard vb;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		vboxes = new HashMap<String, VBox>();
		vboxes.put("ValveEnable", ValveEnable_S2_VBox);
		vboxes.put("TurboNLK", TurboNLK_m_S3_VBox);
		vboxes.put("BLV", BLV_AutoClose_S7_VBox);
		vboxes.put("PV", PV_AutoClose_S7_VBox);
		vboxes.put("LockValve", LockValve_AutoClose_S8_VBox);
		vboxes.put("LockRoughingValve", LockRoughingValve_AutoClose_S9_VBox);
		vboxes.put("TurboRoughValve", TurboRoughValve_AutoClose_S10_VBox);
		vboxes.put("VentValve", VentValve_AutoClose_S11_VBox);
		vboxes.put("VentLockChamber", VentLockChamber_m_S12_VBox);
		vboxes.put("VentLock", VentLock_m_S11_VBox);
		vboxes.put("PumpLockChamber", PumpLockChamber_m_S14_VBox);
		
		leds = new HashMap<LED, Rectangle>();
		InitializeLEDs();
		
		
		 VentValve_LED16_LED.setFill(Color.RED);
		 
		 heartbeatCount = 0;
		 
		 //make the virtual board
		 InitializeLEDs();
	}
	
	
	private void InitializeLEDs() {
		//initialize the LEDs
		//TODO: This one might have to be a special case, as it'll have more than 3 colors
//		 LED HeartBeat_LED0 = new LED("Program Running", Color.RED, Color.GREEN, Color.PINK, "", "", false);
		 
		 //TODO: Figure out if it's a problem to have one condition and an else clause instead of 2
		 //TODO-2: we'll probably need to make some if/else check to make sure cond2 isn't "", that should work
		 LED Test24V_LED1 = new LED("24V on", Color.GREEN, null, Color.GRAY, "Test24V_Denkovi0_19", "", true);
		 LED ValveEnable_LED2 = new LED("Valves Enabled", Color.YELLOW, null, Color.GRAY, "ValveEnable_VR1", "", true);
		 LED Interlock_LED3 = new LED("Bypass", Color.RED, null, Color.GRAY, "TurboNLK_VR104", "", true);
		 LED BLValve_LED5 = new LED("Beam Line Valve", Color.GREEN, Color.RED, Color.GRAY, "BLV_open_J1_0", "BLV_closed_J1_1", true);
		 LED ChamHighVac_LED6 = new LED("Chamber at High Vacuum", Color.GREEN, Color.RED, null, "IGltSP_VR103", "!IGltSP_VR103", false);
		 LED TC2_LED7 = new LED("Chamber at Rough Vacuum", Color.GREEN, Color.RED, null, "TC2ltSP_VR203", "!TC2ltSP_VR203", false);
		 LED PumpValve_LED8 = new LED("Pump Valve", Color.GREEN, Color.RED, Color.GRAY, "PV_open_J2_2", "PV_closed_J2_3", true);
		 LED TurboON_LED9 = new LED("Turbo at Speed", Color.YELLOW, null, Color.GRAY, "TurboatSpeed_VR202", "", true);
		 LED LockAtm_LED10 = new LED("Lock Vented", Color.YELLOW, null, Color.GRAY, "!TC3ltSP2_VR102", "", true);
		 LED TC3_LED11 = new LED("Lock at Vacuum", Color.GREEN, Color.RED, null, "TC3ltSP1_VR301", "!TC3ltSP1_VR301", false);
		 LED LockValve_LED12 = new LED("Lock Valve", Color.GREEN, Color.RED, Color.GRAY, "LVopen_VR501", "LVclosed_VR502", true);
		 LED TC1_LED14 = new LED("Turbo Backing", Color.GREEN, Color.RED, null, "TC1ltSP_VR201", "!TC1ltSP_VR201", false);
		 //TODO: Color for fault?  Currently it's black
		 LED TurboRoughValve_LED15 = new LED("Turbo Rough Valve", Color.GREEN, Color.RED, Color.BLACK, "TurboRoughopen_VR601", "TurboRoughclosed_VR602", true);
		 LED VentValve_LED16 = new LED("Vent Valve", Color.GREEN, Color.RED, null, "LOGIC_VentValveopen_VR303", "!LOGIC_VentValveopen_VR303", false);
		 //TODO-2 also fault state is black here
		 LED LockRoughValve_LED17 = new LED("Lock Rough Valve", Color.GREEN, Color.RED, Color.BLACK, "LockRoughopen_VR503", "LockRoughcloses_VR504", true);
		 LED VentLockCham_LED18 = new LED("Venting Lock and Chamber", Color.YELLOW, null, Color.GRAY, "VendLockCham_m_S12", "", true);
		 LED VentLock_LED19 = new LED("Venting Lock", Color.YELLOW, null, Color.GRAY, "VentLock_m_S13", "", true);
		 //TODO: Ask DeYoung if this one needs a label
		 LED LockChamPumping_LED20 = new LED("", Color.YELLOW, null, Color.GRAY, "PumpLockChamber_m_S14", "", true);
		 
		 
		 //Add LEDS to leds Array
		 //leds.add(,Test24V_LED1);
		  leds.put(ValveEnable_LED2, ValveEnable_LED2_LED);
		 leds.put(Interlock_LED3, Interlock_LED3_LED_LED);
		 leds.put(BLValve_LED5, BLValve_LED5_LED);
		 leds.put(ChamHighVac_LED6, ChamHighVac_LED6_LED);
		 leds.put(TC2_LED7, TC2_LED7_LED);
		 leds.put(PumpValve_LED8, PumpValve_LED8_LED);
		 leds.put( TurboON_LED9, TurboON_LED9_LED);
		 leds.put(LockAtm_LED10, LockAtm_LED10_LED);
		 //leds.put(TC3_LED11_LED, TC3_LED11);
		 leds.put(LockValve_LED12, LockValve_LED12_LED);
		 leds.put(TC1_LED14, TC1_LED14_LED);
		 leds.put(TurboRoughValve_LED15, TurboRoughValve_LED15_LED);
		 leds.put(VentValve_LED16, VentValve_LED16_LED);
		 leds.put(LockRoughValve_LED17, LockRoughValve_LED17_LED);
		 leds.put(VentLockCham_LED18, VentLockCham_LED18_LED);
		 leds.put(VentLock_LED19, VentLock_LED19_LED);
		 leds.put(LockChamPumping_LED20, LockChamPumping_LED20_LED);
		
	}

	// ---------Button Methods----------------------
	@FXML
	private void buttonPressed(ActionEvent event) {
		Button button = (Button) event.getSource();
		if (button.isDisable() == true) {
			return;
		} else {
			VBox box = getVBoxFromButton(button);
			updateVBoxButtons(button, box);
		}
	}

	private void updateVBoxButtons(Button button, VBox box) {
		for (javafx.scene.Node child : box.getChildren()) {
			if (child.getClass() == Button.class) {
				if (child == button) {
					child.setDisable(true);
				} else {
					child.setDisable(false);
				}
			}
		}
	}

	private VBox getVBoxFromButton(Button button) {
		String id = button.idProperty().getValue().toString();
		String name = id.split("_")[0];
		return vboxes.get(name);
	}

	
	//------------Main Controller Methods
	@FXML
	private void startApp(ActionEvent event) {
		Timeline timeline = new Timeline(); 
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						readGPIO();
					}
				})
				);
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						readVirtualSwitches();
					}
				})
				);
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						updateVirtualRelays();
					}
				})
				);
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						updateRealRelays();
					}
				})
				);
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						updateLEDs();
					}
				})
				);
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						incrementHeartbeatColor();
					}
				})
				);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.playFromStart();
				
	}


//---------Heartbeat Methods-------------------

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
	
	private void readGPIO() {
		System.out.println("Reading GPIO");
	}
	
	private void readVirtualSwitches() {
		System.out.println("Reading Virtual Switches");
	}
	
	private void updateVirtualRelays() {
		System.out.print("Updating Virual Relays");
	}
	
	private void updateRealRelays() {
		System.out.println("Updating Real Relays");
	}
	
	private void updateLEDs() {
		System.out.println("Updating LEDS");
		for(LED led : leds.keySet()) {
			if (led != null) {
				Rectangle vled = leds.get(led);
				if(vled != null) {
			vled.setFill(led.getCurrentColor());
		}
			}
		}
	}
}
