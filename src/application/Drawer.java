//package application;
///**
// * Implements drawing methods on a single JFrame using a single Graphics 
// * passed in via the input 
// */
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.FlowLayout;
//import java.awt.Graphics;
//import java.awt.Point;
//
//import javax.swing.JFrame;
//
//public class Drawer {
//
//	private VirtualBoard vb;
//	static Graphics g;
//	static JFrame frame;
//	
//	
//	public Drawer(VirtualBoard vb, JFrame frame, Graphics g) {
//		
//		this.vb = vb;
//		this.g = g;
//		this.frame = frame;
//		
////		 //Create and set up the window.
////        JFrame frame = new JFrame("System");
////        frame.setSize(500, 500);
////        frame.setLayout(new FlowLayout());
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        //Display the window
////        frame.setVisible(true);
////		
////        //generate a Graphics object to draw things
////        
////        Container contentPane = new Container();
////        contentPane.setSize(500, 300);
////        frame.add(contentPane, BorderLayout.CENTER);
////        contentPane.setVisible(true);
////        Graphics g = contentPane.getGraphics();
//	}
//	
//	/**
//	 * Draws a square of color stored in led
//	 * @param led - led to draw
//	 */
//    public static void drawLED(LED led) {
//    	if (led != null) {
//    		g.setColor(led.getColor());
//    		g.fillRect(led.getCoordinates().x, led.getCoordinates().y, 10, 10);
//    	}
//    }
//	
//	
//	//----Functions for drawing switches----
//    /**
//     * Draws a switch open or closed depending on value of vSwitch
//     * @param vswitch - the switch to be drawn
//     */
//	public static void drawSwitch(VirtualSwitch vSwitch) {
//    	if(vSwitch != null) {
//		if (vSwitch.getStatus() == SwitchStatus.OPEN) {
//    		drawSwitchOpen(vSwitch.getCoordinates());
//    		System.out.println(vSwitch.getName() + ": Open");
//    	} else {
//    		drawSwitchClosed( vSwitch.getCoordinates());
//    		System.out.println(vSwitch.getName() + ": Closed");
//    	}
//    	}
//    }
//	
//	/**
//	 * Helper function for drawing a switch closed
//	 * @param coordinate - the coordinate of where the switch is drawn 
//	 */
//	private static void drawSwitchClosed(Point coordinate) {
//    	int x = coordinate.x;
//    	int y = coordinate.y;
//        g.setColor(Color.black);
//        g.fillRect(x, y, 10, 10);
//        g.drawLine(x + 10, y, x + 40, y);
//        g.fillRect(x+40, y, 10, 10);
//    }
//	
//	/**
//	 * Helper function for drawing a switch open
//	 * @param coordinate - the coordinate of where the switch is drawn 
//	 */
//	  private static void drawSwitchOpen(Point coordinate) {
//	    	int x = coordinate.x;
//	    	int y = coordinate.y;
//	        g.setColor(Color.black);
//	        g.fillRect(x, y, 10, 10);
//	        g.drawLine(x + 10, y, x + 35, y - 15);
//	        g.fillRect(x+40, y, 10, 10);
//	        
//	    }
//	
//	  public void drawAll() {
//		g.clearRect(0, 0, (int)frame.getSize().getWidth(), (int)frame.getSize().getHeight());
//		  for(LED led : vb.getLEDs()) {
//			  drawLED(led);
//		  }
//		  
//		  for(VirtualSwitch vSwitch: vb.getVirtualSwitches()){
//			  drawSwitch(vSwitch);
//		  }
//		  
//	  }
//	  
//}
