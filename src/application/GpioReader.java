package application;

import java.io.IOException;
import java.util.Arrays;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

public class GpioReader {

	// To Execute this program you need root access, which is challenging to
	// enable in Eclipse
	// To Execute from command line, navigate to the /bin in the project
	// directory and paste in the following command:
	// sudo java -classpath .:/opt/pi4j/lib/'*' Main
	// You do need to try and run it from eclipse first though. That compiles
	// the classfiles.

	private final GpioController gpio;
	private MCP23017GpioProvider bus_20;
	private MCP23017GpioProvider bus_21;

	public GpioReader() throws UnsupportedBusNumberException, IOException {
		// create gpio controller
		gpio = GpioFactory.getInstance();
		System.out.println("made gpio");

		// Create a custom gpio provider
		// Tutorial 2 uses bus 2 with an address of 0x20 so I think this is the
		// right setup for that.
		bus_20 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x20);
		bus_21 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x21);

		System.out.println("made providers");
	}

	public int[] getGPIOStatus() {
		// get gpio pins from MCP23017
		// We're just going to get one input for this test

		// Create an input pin attached to provider_1
		// I think that this is the same as finding pin 1 in the Python example,
		// and just the indexing here starts at 0 instead of 1
		GpioPinDigitalInput input[] = {
				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A0, "MyInput-20-A1", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A1, "MyInput-20-A2", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A2, "MyInput-20-A3", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A3, "MyInput-20-A4", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A4, "MyInput-20-A5", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A5, "MyInput-20-A6", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A6, "MyInput-20-A7", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_A7, "MyInput-20-A8", PinPullResistance.PULL_UP),

				// I think this is the correct way to refer to pins 9 and 10,
				// but it could be that I'm making it more complicated than it
				// needs to be

				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_B0, "MyInput-20-A9", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_20, MCP23017Pin.GPIO_B1, "MyInput-20-A10", PinPullResistance.PULL_UP),

				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A0, "MyInput-21-A1", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A1, "MyInput-21-A2", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A2, "MyInput-21-A3", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A3, "MyInput-21-A4", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A4, "MyInput-21-A5", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A5, "MyInput-21-A6", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A6, "MyInput-21-A7", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_A7, "MyInput-21-A8", PinPullResistance.PULL_UP),

				// I think this is the correct way to refer to pins 9 and 10,
				// but it could be that I'm making it more complicated than it
				// needs to be

				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_B0, "MyInput-21-A9", PinPullResistance.PULL_UP),
				gpio.provisionDigitalInputPin(bus_21, MCP23017Pin.GPIO_B1, "MyInput-21-A10",
						PinPullResistance.PULL_UP), };

		System.out.println("made input");

		// Loop forever, checking states ever 2 seconds
		int states[] = new int[20];
		GpioPinDigitalInput pin = input[0];
		PinState state = pin.getState();
		// Turn off pin 8 whenever the button hasn't been pressed.
//		for (int i = 0; i < 20; i++) {
		
		// RVlock_open_J5_8
			pin = input[18];
			state = pin.getState();
			if (state == PinState.HIGH) {
				states[18] = 1;
			} else {
				states[18] = 0;
			}
			
			// RVlock_closed_J5_9
			pin = input[19];
			state = pin.getState();
			if (state == PinState.HIGH) {
				states[19] = 1;
			} else {
				states[19] = 0;
			}

//		}
		System.out.println(Arrays.toString(states));
		return states;
	}
}