package application;


public class LEDHandler {
  private ArrayList<LED> leds;

  public LEDHandler() {
    this.leds = new ArrayList<LED>();
  }

  public void addLED(String label, Color cond1Color, Color cond2Color, Color elseColor, String cond1, String cond2, boolean elseClause, Rectangle gui_Rect) {
    LED led = new LED(String label, cond1Color, cond2Color, elseColor, cond1, cond2, elseClause, gui_Rect);
    leds.add(led);
  }

  public void updateLEDs(HashMap<String, boolean> virtualRelays) {
    for(LED led: leds) {
      boolean cond1 = virtualRelays.get(led.getCond1());
      boolean cond2 = virtualRelays.get(led.getCond2());
      led.update(cond1, cond2);
    }

  }

}
