package application;
import javafx.scene.paint.Color;
import java.awt.Point;
import java.util.function.Function;



/**
 * Class to represent LED lights on the virtual board
 * 
 * Intended to indicate if certain sections of the system are ready for pressure changing instructions or not
 * @author Joanie Davis
 *
 */
public class LED {

	//The label of the LED that will appear on the gui
	private String label;
	
	//The color of the LED if condition 1 is true
	private Color cond1Color;
	
	//The color of the LED if condition 2 is true
	private Color cond2Color;
	
	//The color of the LED if the else clause is met
	//if there is no else clause, can be set to null
	private Color elseColor;
	
	//The current color that should be displayed on the gui
	private Color currentColor;
	
	//A string used as a key to retrieve the value of the first condition
	private String cond1;
	
	//A string used as a key to retrieve the value of the second condition 
	private String cond2;
	
	//A boolean indicating if there's a third state the LED can have if the other two conditions are not met
	private boolean elseClause;
	
	
	public LED(String label, Color green, Color cond2Color, Color gray, String cond1, String cond2, boolean elseClause) {
		this.label = label;
		this.cond1Color = green;
		this.cond2Color = cond2Color;
		this.elseColor = gray;
		this.currentColor = green;
		this.cond1 = cond1;
		this.cond2 = cond2;
		this.elseClause = elseClause;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Color getCurrentColor() {
		return currentColor;
	}
	
	public String getCond1() {
		return cond1;
	}
	
	public String getCond2() {
		return cond2;
	}
	
	public boolean hasElseClause() {
		return elseClause;
	}
}