package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.Node;

import com.sun.javafx.geom.Rectangle;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
	public Pane HeartBeat_LED0_LED;
//	private int heartbeatCount;

	private HashMap<String, VBox> vboxes;

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
		HeartBeat_LED0_LED.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		// startHeartbeat();
	}

//	// ---------Heartbeat Methods-------------------
	
	public Pane getHeartBeatPane() {
		return HeartBeat_LED0_LED;
	}
//	private void startHeartbeat() {
//		heartbeatCount = 0;
//		while (true) {
//			// try {
//			// wait(10);
//			// } catch (InterruptedException e) {
//			// // TODO Auto-generated catch block
//			// e.printStackTrace();
//			// }
//			incrementHeartbeatColor();
//		}
//	}
//
//	private void incrementHeartbeatColor(Pane pane) {
//		switch (heartbeatCount) {
//		case 0:
//			HeartBeat_LED0_LED.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
//			heartbeatCount++;
//			break;
//		case 1:
//			HeartBeat_LED0_LED.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
//			heartbeatCount++;
//			break;
//		case 2:
//			HeartBeat_LED0_LED.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
//			heartbeatCount++;
//			break;
//		case 3:
//			HeartBeat_LED0_LED.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
//			heartbeatCount++;
//			break;
//		case 4:
//			HeartBeat_LED0_LED.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
//			heartbeatCount++;
//			break;
//		case 5:
//			HeartBeat_LED0_LED.setBackground(new Background(new BackgroundFill(Color.PURPLE, null, null)));
//			heartbeatCount = 0;
//			break;
//
//		}
//	}

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

}
