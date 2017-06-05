//package application;
///*Class that handles all of the other classes in the system
// * Main function includes generating all of the gui 
// * 	Checking input from the pins
// * 	Adjusting virtual constructs to match input data from the pins
// * 	
// *  --Basically this is a test version of the system to make sure I know how Java GUI works still--
// */
//
////import javax.swing.*;
////
////import java.awt.BorderLayout;
////import java.awt.Color;
////import java.awt.Container;
////import java.awt.FlowLayout;
////import java.awt.Graphics;
////import java.awt.Point;
////import java.awt.event.ActionEvent;
////import java.awt.event.ActionListener;
////import java.awt.geom.*;
////
////public class VirtualBoard {
////
////	static Drawer d;
////    
////    /**
////     * Main function of the System
////     * Executed when the program runs 
////     * @param args
////     */
////    public static void main(String[] args) {
////                //createAndShowGUI(); 
////    	 //Create and set up the window.
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
////        
////        //create the drawer 
////        d = new Drawer(g, frame);
////
////        //Create a Switch to have it's state changed
////        VirtualSwitch vSwitch = new VirtualSwitch("TestSwitch", new Point(100, 150), SwitchStatus.CLOSED);
////        LED led = new LED("test_led", new Point(200, 150), Color.RED, null);
////       JButton b1 = new JButton("Button");
////       b1.addActionListener(new ActionListener(){
////    	   public void actionPerformed(ActionEvent e)
////    	   {
////    		   changeSwitch(vSwitch, led);
////    	   }
////       });
////       b1.setSize(75,30);
////       frame.add(b1, BorderLayout.PAGE_END);
////       b1.setVisible(true);
////    }
////    
////    
////    private static void changeSwitch(VirtualSwitch vSwitch, LED led) {
////    	   Drawer.drawSwitch(vSwitch);
////		   Drawer.drawLED(led);
////		   if (vSwitch.getStatus() == SwitchStatus.OPEN) {
////			   vSwitch.setStatus(SwitchStatus.CLOSED);
////			   led.setColor(Color.GREEN);
////		   } else {
////			   vSwitch.setStatus(SwitchStatus.OPEN);
////			   led.setColor(Color.RED);
////		   }
////    }
////    
////}
///*Class that handles all of the other classes in the system
// * Main function includes generating all of the gui 
// * 	Checking input from the pins
// * 	Adjusting virtual constructs to match input data from the pins
// * 	
// *  --Basically this is a test version of the system to make sure I know how Java GUI works still--
// */
//
//import javax.swing.*;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Graphics;
//import java.awt.Point;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.geom.*;
//
//public class OverallSystem {
//
//	//static Drawer d;
//	static VirtualBoard vb;
//	static Graphics g;
//    static JFrame frame;
//    /**
//     * Main function of the System
//     * Executed when the program runs 
//     * @param args
//     */
////    public static void main(String[] args) {
////       vb = new VirtualBoard();
////        
////      //Create and set up the window.
////      frame = new JFrame("System");
////      frame.setSize(500, 500);
////      frame.setLayout(new BorderLayout());
////      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////      
////      //Display the window
////      frame.setVisible(true);
////      Container contentPane = frame.getContentPane();
////      contentPane.setSize(500, 500);
////      contentPane.setVisible(true);
////      
////      //generate a Graphics object to draw things
////      
////      Container drawPane = new Container();
////      drawPane.setSize(500, 350);
////      contentPane.add(drawPane, BorderLayout.PAGE_END);
////      drawPane.setVisible(true);
////      g = drawPane.getGraphics();
////      
////      //create the drawer 
////       // Drawer d = new Drawer(vb, frame, g);
////        
////        //Create a frame to hold the buttons
////        Container buttonPane = new Container();
////        buttonPane.setSize(500, 150);
////        buttonPane.setLayout(new BorderLayout());
////        buttonPane.setVisible(true);
////        contentPane.add(buttonPane, BorderLayout.PAGE_START);
////        contentPane.paint(g);
////        
////      //Make the buttons  
////      JButton b1 = new JButton("Switch 1");
////      b1.addActionListener(new ActionListener(){
////   	   public void actionPerformed(ActionEvent e)
////   	   {
////   		   vb.changeSwitch(0);
////   		   drawAll();
////   		   checkStatus();
////   	   }
////      });
////      
////      JButton b2 = new JButton("Switch 2");
////      b2.addActionListener(new ActionListener(){
////   	   public void actionPerformed(ActionEvent e)
////   	   {
////   		   vb.changeSwitch(1);
////   		   drawAll();
////   		   checkStatus();
////   	   }
////      }); 
////      
////      
////      JButton b3 = new JButton("Switch 3");
////      b3.addActionListener(new ActionListener(){
////   	   public void actionPerformed(ActionEvent e)
////   	   {
////   		   vb.changeSwitch(2);
////   		   drawAll();
////   		   checkStatus();
////   		   
////   	   }
////      });
////      
////
////      JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
////      flowPanel.add(b1);
////      flowPanel.add(b2);
////      flowPanel.add(b3);
////      buttonPane.add(BorderLayout.PAGE_END, flowPanel);
////      
////      JLabel label = new JLabel("Test");
////      label.setText("GUI Mockup");
////      JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
////      textPanel.add(label);
////      buttonPane.add(BorderLayout.PAGE_START, textPanel);
////      label.setVisible(true);
////
////      b1.setVisible(true);
////      b1.setSize(75,50);
////      b2.setVisible(true);
////      b2.setSize(75,50);
////      b3.setVisible(true);
////      b3.setSize(75,50);
////      drawPane.paint(g);
////      
////      
////       packFrame(frame);
////       drawAll();
////    }
//    
//    private static void checkStatus() {
//    	SwitchStatus overallStatus = vb.getStatus();
//    	if (overallStatus == SwitchStatus.OPEN) {
//    		//Print something saying they're all open!
//    		System.out.println("\nAll Switches are open!\n");
//    		   JOptionPane.showMessageDialog(frame,
//                       "All Three Switches are open!", null, 1);
//    	} else if (overallStatus == SwitchStatus.CLOSED) {
//    		//Print something saying they're all closed
//    		System.out.println("\nAll Switches are closed\n");
//    		JOptionPane.showMessageDialog(frame,
//                    "All Three Switches are closed!", null, 1);
//    	} else {
//    		//keep the text box invisible.  
//    	}
//    }
//    
//
//
//	private static void packFrame(JFrame frame) {
//
//        //This keeps the frame from resizes when pack() is called
//		Dimension r = frame.getSize();
//           frame.setExtendedState(java.awt.Frame.NORMAL);
//           frame.pack();
//           frame.setSize(r);
//	}
//	
//	
//	
//	
//	
//	//-------------DRAWER STUFF ------------------
//	
//	 public static void drawLED(LED led) {
//	    	if (led != null) {
//	    		g.setColor(led.getColor());
//	    		g.fillRect(led.getCoordinates().x, led.getCoordinates().y, 10, 10);
//	    	}
//	    }
//		
//		
//		//----Functions for drawing switches----
//	    /**
//	     * Draws a switch open or closed depending on value of vSwitch
//	     * @param vswitch - the switch to be drawn
//	     */
//		public static void drawSwitch(VirtualSwitch vSwitch) {
//	    	if(vSwitch != null) {
//			if (vSwitch.getStatus() == SwitchStatus.OPEN) {
//	    		drawSwitchOpen(vSwitch.getCoordinates());
//	    		System.out.println(vSwitch.getName() + ": Open");
//	    	} else {
//	    		drawSwitchClosed( vSwitch.getCoordinates());
//	    		System.out.println(vSwitch.getName() + ": Closed");
//	    	}
//	    	}
//	    }
//		
//		/**
//		 * Helper function for drawing a switch closed
//		 * @param coordinate - the coordinate of where the switch is drawn 
//		 */
//		private static void drawSwitchClosed(Point coordinate) {
//	    	int x = coordinate.x;
//	    	int y = coordinate.y;
//	        g.setColor(Color.black);
//	        g.fillRect(x, y, 10, 10);
//	        g.drawLine(x + 10, y, x + 40, y);
//	        g.fillRect(x+40, y, 10, 10);
//	    }
//		
//		/**
//		 * Helper function for drawing a switch open
//		 * @param coordinate - the coordinate of where the switch is drawn 
//		 */
//		  private static void drawSwitchOpen(Point coordinate) {
//		    	int x = coordinate.x;
//		    	int y = coordinate.y;
//		        g.setColor(Color.black);
//		        g.fillRect(x, y, 10, 10);
//		        g.drawLine(x + 10, y, x + 35, y - 15);
//		        g.fillRect(x+40, y, 10, 10);
//		        
//		    }
//		
//		  public static void drawAll() {
//			g.clearRect(0, 0, (int)frame.getSize().getWidth(), (int)frame.getSize().getHeight());
//			  for(LED led : vb.getLEDs()) {
//				  drawLED(led);
//			  }
//			  
//			  for(VirtualSwitch vSwitch: vb.getVirtualSwitches()){
//				  drawSwitch(vSwitch);
//			  }
//			  //packFrame(frame);
//		  }
//}
//
//
//
//
//
//
