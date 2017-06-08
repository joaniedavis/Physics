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
	private ArrayList<Rectangle> leds;

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
		
		leds = new ArrayList<Rectangle>();
		 //leds.add(Test24V_LED1_LED);
		 leds.add(ValveEnable_LED2_LED);
		// leds.add(Interlock_LED3_LED);
		 leds.add(BLValve_LED5_LED);
		 leds.add(ChamHighVac_LED6_LED);
		 leds.add(PumpValve_LED8_LED);
		 leds.add(TurboON_LED9_LED);
		 leds.add(LockAtm_LED10_LED);
		//leds.add(TC3_LED11_LED);
		 leds.add(TC1_LED14_LED);
		 leds.add(TurboRoughValve_LED15_LED);
		 leds.add(VentValve_LED16_LED);
		 leds.add(LockRoughValve_LED17_LED);
		 leds.add(VentLockCham_LED18_LED);
		 leds.add(VentLock_LED19_LED);
		 leds.add(LockChamPumping_LED20_LED);
		
		
		 VentValve_LED16_LED.setFill(Color.RED);
		 
		 heartbeatCount = 0;
		 //startHeartbeat();
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
}
