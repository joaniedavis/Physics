//package application.tests;
//
//import java.util.HashMap;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import application.LED;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//
//public class LEDMethodsTest {
//
//	// Need to initialize JavaFX toolkit before any JavaFX elements are test
//	@BeforeClass
//	public static void initToolkit() {
//		new JFXPanel(); // initializes JavaFX environment
//	}
//
//	@Test
//	public void testNo2Clause() {
//		LED no2ClauseLED = new LED("No 2 Clause", Color.GREEN, null, Color.RED, "Single Condition", "", false, new Rectangle());
//		
//		no2ClauseLED.update(true, false);
//		assert(no2ClauseLED.getCurrentColor().equals(Color.GREEN));
//		no2ClauseLED.update(false, true);
//		assert(no2ClauseLED.getCurrentColor().equals(Color.RED));
//	}
//	
//	@Test
//	public void testYes2Clause() {
//		LED yes2ClauseLED = new LED("Yes 2 Clause", Color.GREEN, Color.YELLOW, Color.RED, "First Condition", "Second Condition", true, new Rectangle());
//		
//		yes2ClauseLED.update(true, false);
//		assert(yes2ClauseLED.getCurrentColor().equals(Color.GREEN));
//		yes2ClauseLED.update(false, true);
//		assert(yes2ClauseLED.getCurrentColor().equals(Color.YELLOW));
//		yes2ClauseLED.update(false, false);
//		assert(yes2ClauseLED.getCurrentColor().equals(Color.RED));
//	}
//	
//	@Test
//	public void testEquals() {
//		LED no2ClauseLED = new LED("No 2 Clause", Color.GREEN, null, Color.RED, "Single Condition", "", false, new Rectangle());
//		LED yes2ClauseLED = new LED("Yes 2 Clause", Color.GREEN, Color.YELLOW, Color.RED, "First Condition", "Second Condition", true, new Rectangle());
//		assert(new LED("No 2 Clause", Color.GREEN, null, Color.RED, "Single Condition", "", false, new Rectangle()).equals(no2ClauseLED));
//		assert(!yes2ClauseLED.equals(no2ClauseLED));
//	}
//
//}
