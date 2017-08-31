package application;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LEDHandler {
  private ArrayList<LED> leds;

  public LEDHandler() {
    this.leds = new ArrayList<LED>();
  }

  public void addLED(String label, Color cond1Color, Color cond2Color, Color elseColor, String cond1, String cond2, boolean has2Clause, Rectangle gui_Rect) {
    LED led = new LED(label, cond1Color, cond2Color, elseColor, cond1, cond2, has2Clause, gui_Rect);
    leds.add(led);
  }

  public void updateLEDs(HashMap<String, Boolean> virtualRelays) {
    for(LED led: leds) {
      boolean cond1 = virtualRelays.get(led.getCond1());
      if(led.has2clause()) {
    	  boolean cond2 = virtualRelays.get(led.getCond2());
          led.update(cond1, cond2);
      } else {
    	  led.update(cond1, false);
      }
    }

  }

  public ArrayList<LED> getLEDs() {
	  return leds;
  }

}
