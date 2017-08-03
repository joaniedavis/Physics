//package application;
//
//import java.io.IOException;
//
//import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
//import com.pi4j.gpio.extension.mcp.MCP23017Pin;
//import com.pi4j.io.gpio.GpioController;
//import com.pi4j.io.gpio.GpioFactory;
//import com.pi4j.io.gpio.GpioPinDigitalInput;
//import com.pi4j.io.gpio.PinPullResistance;
//import com.pi4j.io.gpio.PinState;
//import com.pi4j.io.i2c.I2CBus;
//import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
//
//public class GpioReader {
//	
//	static GpioController gpio;
//	static MCP23017GpioProvider bus_20;
//	static MCP23017GpioProvider bus_21;
//
//	public GpioReader() {
//		// create gpio controller
//		final GpioController gpio = GpioFactory.getInstance();
//		System.out.println("made gpio");
//
//		// Create a custom gpio provider
//		// Tutorial 2 uses bus 2 with an address of 0x20 so I think this is the
//		// right setup for that.
//		try {
//			MCP23017GpioProvider bus_20 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x20);
//			MCP23017GpioProvider bus_21 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x21);
//		} catch (UnsupportedBusNumberException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public static int[] read() throws InterruptedException, UnsupportedBusNumberException, IOException {
//		// get gpio pins from MCP23017
//		// We're just going to get one input for this test
//
//		// Create an input pin attached to provider_1
//		// I think that this is the same as finding pin 1 in the Python example,
//		// and just the indexing here starts at 0 instead of 1
//		GpioPinDigitalInput input[] = {
//				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A0, "MyInput-20-A1", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A1, "MyInput-20-A2", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A2, "MyInput-20-A3", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A3, "MyInput-20-A4", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A4, "MyInput-20-A5", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A5, "MyInput-20-A6", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A6, "MyInput-20-A7", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A7, "MyInput-20-A8", PinPullResistance.PULL_UP),
//
//				// I think this is the correct way to refer to pins 9 and 10,
//				// but it could be that I'm making it more complicated than it
//				// needs to be
//
//				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_B0, "MyInput-20-A9", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_B1, "MyInput-20-A10", PinPullResistance.PULL_UP),
//
//				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A0, "MyInput-21-A1", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A1, "MyInput-21-A2", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A2, "MyInput-21-A3", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A3, "MyInput-21-A4", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A4, "MyInput-21-A5", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A5, "MyInput-21-A6", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A6, "MyInput-21-A7", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A7, "MyInput-21-A8", PinPullResistance.PULL_UP),
//
//				// I think this is the correct way to refer to pins 9 and 10,
//				// but it could be that I'm making it more complicated than it
//				// needs to be
//
//				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_B0, "MyInput-21-A9", PinPullResistance.PULL_UP),
//				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_B1, "MyInput-21-A10",
//						PinPullResistance.PULL_UP), };
//
//		System.out.println("made input");
//
//		// Loop forever, checking states ever 2 seconds
//		int states[] = new int[20];
//		GpioPinDigitalInput pin = input[0];
//		PinState state = pin.getState();
//		while (true) {
//			System.out.println(" listening....");
//			// Turn off pin 8 whenever the button hasn't been pressed.
//			for (int i = 0; i < 20; i++) {
//				pin = input[i];
//				state = pin.getState();
//				if (state == PinState.HIGH) {
//					states[i] = 1;
//				} else {
//					states[i] = 0;
//				}
//
//			}
//			return states;
//		}
//
//	}
//}
