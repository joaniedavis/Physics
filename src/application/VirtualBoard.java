package application;
/*Class that handles all of the other classes in the system
 * Main function includes generating all of the gui 
 * 	Checking input from the pins
 * 	Adjusting virtual constructs to match input data from the pins
 * 	
 *  --Basically this is a test version of the system to make sure I know how Java GUI works still--
 */

import javax.swing.*;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;

public class VirtualBoard {
	private LED blvLED;
	private LED hiVacLED;
	private LED turboLED;
	
	private ArrayList<LED> leds = new ArrayList<LED>();
	private ArrayList<VirtualSwitch> vSwitches = new ArrayList<VirtualSwitch>();
	private int heartbeatCount;
	private Color heartbeatColor;
	
	public VirtualBoard() {
		//Initialize vSwitches
		InitializeVirtualSwitches();
		
		//TODO: Create a hash of Conditions (String condition labels mapped to boolean values), and initialize it here
		
		
		//Initialize LED objects
		InitializeLEDs();

	}
	
	/*
	 * Initializes all of the VirtualSwitch Objects and adds them to the vSwitches ArrayList
	 */
	private void InitializeVirtualSwitches() {

		//initialize the VirtualSwitches
		VirtualSwitch ValveEnable_S2 = new VirtualSwitch("Enable Valves", "Enabled", "Disabled", SwitchState.FALSE, false);
		VirtualSwitch TurboNLK_m_S3 = new VirtualSwitch("Turbo Pump Interlock", "Bypass", "Normal", SwitchState.FALSE, false);
		VirtualSwitch BLV_AutoClose_S6 = new VirtualSwitch("Beam Line Valve", "Auto", "Close", SwitchState.FALSE, false);
		VirtualSwitch PV_AutoClose_S7 = new VirtualSwitch("Turbo Pump Valve", "Auto", "Close", SwitchState.FALSE, false);
		VirtualSwitch LockValve_AutoClose_S8 = new VirtualSwitch("Lock Valve", "Auto", "Close", SwitchState.FALSE, false);
		VirtualSwitch LockRoughValve_AutoClose_S9 = new VirtualSwitch("Lock Roughing Valve", "Auto", "Close", SwitchState.FALSE, false);
		VirtualSwitch TurboRoughValve_AutoClose_S10 = new VirtualSwitch("Turbo Pump Rough Valve", "Auto", "Close", SwitchState.FALSE, false);
		VirtualSwitch VentValve_AutoClose_S11 = new VirtualSwitch("Vent Valve", "Auto", "Close", SwitchState.FALSE, false);
		VirtualSwitch VentLockChamber_m_S12 = new VirtualSwitch("Vent Lock and Chamber", "Vent", "Standby", SwitchState.FALSE, true);
		VirtualSwitch VentLock_m_S11 = new VirtualSwitch("Vent Lock", "Vent", "Standby", SwitchState.FALSE, true);
		VirtualSwitch PumpLockChamber_m_S14 = new VirtualSwitch("Pump Lock and Chamber", "Pump", "Standby", SwitchState.FALSE, false);
		
		 //Add switches to vSwitches
		 vSwitches.add(ValveEnable_S2);
		 vSwitches.add(TurboNLK_m_S3);
		 vSwitches.add(BLV_AutoClose_S6);
		 vSwitches.add(PV_AutoClose_S7);
		 vSwitches.add(LockValve_AutoClose_S8);
		 vSwitches.add(LockRoughValve_AutoClose_S9);
		 vSwitches.add(TurboRoughValve_AutoClose_S10);
		 vSwitches.add(VentValve_AutoClose_S11);
		 vSwitches.add(VentLockChamber_m_S12);
		 vSwitches.add(VentLock_m_S11);
		 vSwitches.add(PumpLockChamber_m_S14);
	}
	
	/*
	 * Initialize a map of all the conditions in the system 
	 */
	private void InitializeConditionMap() {
		
	}
	
	/*
	 * TODO: 
	 * Initializes all of the LED objects and adds them to the leds ArrayList
	 */
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
		 leds.add(Test24V_LED1);
		 leds.add(ValveEnable_LED2);
		 leds.add(Interlock_LED3);
		 leds.add(BLValve_LED5);
		 leds.add(ChamHighVac_LED6);
		 leds.add(PumpValve_LED8);
		 leds.add(TurboON_LED9);
		 leds.add(LockAtm_LED10);
		 leds.add(TC3_LED11);
		 leds.add(TC1_LED14);
		 leds.add(TurboRoughValve_LED15);
		 leds.add(VentValve_LED16);
		 leds.add(LockRoughValve_LED17);
		 leds.add(VentLockCham_LED18);
		 leds.add(VentLock_LED19);
		 leds.add(LockChamPumping_LED20);
		
	}
    
//    public ArrayList<LED> getLEDs() {
//    	return leds;
//    }
//    
//    public ArrayList<VirtualSwitch> getVirtualSwitches() {
//    	return vSwitches;
//    }
    
    //TODO: Hardcode in the formulas that determine the values of each condition
    public void updateConditions() {
    	
    }
    
    
}



