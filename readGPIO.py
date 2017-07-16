import importlib.util
helpers = importlib.util.spec_from_file_location("ABEHelpers", "/src/ABElectronics_Python_Libraries/IOPi/ABEHelpers.py")
helpersMod = importlib.util.module_from_spec(helpers)
helpers.loader.exec_module(helpersMod)
helpersMod.MyClass()


#from ABE_helpers import ABEHelpers
from ABE_IoPi import IoPi
import time

class ReadGPIO:
    
    def run(self):

        i2c_helper = ABEHelpers()
        i2c_bus = i2c_helper.get_smbus()

        bus = IoPi(i2c_bus, 0x20)

        bus.set_pin_direction(1, 1)  # set pin 1 as an input

        bus.set_pin_direction(8, 0)  # set pin 8 as an output

        bus.write_pin(8, 0)  # turn off pin 8

        bus.set_pin_pullup(1, 1)  # enable the internal pull-up resistor on pin 1

        bus.invert_pin(1, 1)  # invert pin 1 so a button press will register as 1


        while True:

            if bus.read_pin(1) == 1:  # check to see if the button is pressed
                print 'button pressed'  # print a message to the screen
                bus.write_pin(8, 1)  # turn on the led on pin 8
                time.sleep(2)  # wait 2 seconds
            else:
                bus.write_pin(8, 0)  # turn off the led on pin 8