package application;
import javafx.scene.paint.Color;
import java.awt.Point;
import java.util.function.Function;
import javafx.scene.shape.Rectangle;



/**
 * Class to represent LED lights on the virtual board
 *
 * Intended to indicate if certain sections of the system are ready for pressure changing instructions or not
 * @author Joanie Davis
 *
 */
public class LED {

	// The label of the LED that will appear on the gui
	private String label;

	// The color of the LED if condition 1 is true
	private Color cond1Color;

	// The color of the LED if condition 2 is true
	private Color cond2Color;

	// The color of the LED if the else clause is met
	// if there is no else clause, can be set to null
	private Color elseColor;

	// The current color that should be displayed on the gui
	private Color currentColor;

	// A string used as a key to retrieve the value of the first condition
	private String cond1;

	// A string used as a key to retrieve the value of the second condition
	private String cond2;

	// A boolean indicating if there is a second condition that should be checked before defaulting to the else clause
	private boolean has2Clause;

	// The corresponding part of the GUI that will have its color changed based on the conditions
	private Rectangle gui_Rect;


	public LED(String label, Color cond1Color, Color cond2Color, Color elseColor, String cond1, String cond2, boolean has2Clause, Rectangle gui_Rect) {
		this.label = label;
		this.cond1Color = cond1Color;
		this.cond2Color = cond2Color;
		this.elseColor = elseColor;
		this.currentColor = Color.green;
		this.cond1 = cond1;
		this.cond2 = cond2;
		this.has2Clause = has2Clause;
		this.gui_Rect = gui_Rect;
	}

	public String getLabel() {
		return label;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public Color getCond1Color() {
		return cond1Color;
	}

	public Color getCond2Color() {
		return cond2Color;
	}

	public Color getElseColor() {
		return elseColor;
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

	public void setCurrentColor(Color color) {
		currentColor = color;
	}

	public void update(boolean cond1, boolean cond2) {
		if(cond1) {
			setCurrentColor(cond1Color);
		} else if (cond2 && has2Clause) {
			setCurrentColor(cond2Color);
		} else {
			setCurrentColor(elseColor);
		}
		gui_Rect.setFill(currentColor);git
	}
}
