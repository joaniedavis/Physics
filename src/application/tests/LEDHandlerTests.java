//package application.tests;
//
//import static org.junit.Assert.*;
//
//import java.util.HashMap;
//
//import application.LED;
//import application.LEDHandler;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//
//public class LEDHandlerTests {
//	
//	private static LEDHandler handler;
//	private static LED noClause2LED;
//	private static LED yesClause2LED;
//
//	// Need to initialize JavaFX toolkit before any JavaFX elements are test
//		@BeforeClass
//		public static void initToolkit() {
//			new JFXPanel(); // initializes JavaFX environment
//			handler = new LEDHandler();
//			noClause2LED = new LED("No 2 Clause", Color.GREEN, null, Color.RED, "Single Condition", "", false, new Rectangle());
//			yesClause2LED = new LED("Yes 2 Clause", Color.GREEN, Color.YELLOW, Color.RED, "First Condition", "Second Condition", true, new Rectangle());
//		}
//
//	
//	@Test
//	public void testAddLED() {
//		handler.addLED("No 2 Clause", Color.GREEN, null, Color.RED, "Single Condition", "", false, new Rectangle());
//		handler.addLED("Yes 2 Clause", Color.GREEN, Color.YELLOW, Color.RED, "First Condition", "Second Condition", true, new Rectangle());
//		LED addedLEDOne = handler.getLEDs().get(0);
//		LED addedLEDTwo = handler.getLEDs().get(1);
//		assert(addedLEDOne).equals(noClause2LED);
//		assert(addedLEDTwo).equals(yesClause2LED);
//	}
//	
//	@Test
//	public void testUpdateLEDs() {
//		handler.addLED("No 2 Clause", Color.GREEN, null, Color.RED, "Single Condition", "", false, new Rectangle());
//		handler.addLED("Yes 2 Clause", Color.GREEN, Color.YELLOW, Color.RED, "First Condition", "Second Condition", true, new Rectangle());
//		
//		HashMap<String, Boolean> testConditions = new HashMap<String, Boolean>();
//		testConditions.put("Single Condition", true);
//		testConditions.put("First Condition", false);
//		testConditions.put("Second Condition", true);
//		
//		handler.updateLEDs(testConditions);
//		
//		LED firstLED = handler.getLEDs().get(0);
//		LED secondLED = handler.getLEDs().get(1);
//		
//		assert(firstLED.getCurrentColor().equals(Color.GREEN));
//		assert(secondLED.getCurrentColor().equals(Color.YELLOW));
//	}
//
//}
