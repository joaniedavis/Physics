package application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DenkoviRelayWriter {
	public static void main(String[] args) {
		// You have to give the entire path to the .jar file
		// TODO: See if it's possible to import the .jar and use a local version
		
		// List command
		String[] list_commands = {"sh","-c","sudo java -jar /$HOME/denkovi/DenkoviRelayCommandLineTool_27.jar list"};
		// Read status of the 1st (1 index) of 8 relays
		String[] status_commands = {"sh","-c","sudo java -jar /$HOME/denkovi/DenkoviRelayCommandLineTool_27.jar DAE003gt 8 1 status"};
		// Set status of the 2nd (1 index) of 8 relays to on (1=on, 0=off)
		String[] set_commands = {"sh","-c","sudo java -jar /$HOME/denkovi/DenkoviRelayCommandLineTool_27.jar DAE003gt 8 1 1"};
		Runtime rt = Runtime.getRuntime();
	
//		Process proc;
		try {
			executeCommands(list_commands, rt);
			executeCommands(status_commands, rt);
			executeCommands(set_commands, rt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void executeCommands(String[] commands, Runtime rt)
			throws IOException {
		Process proc;
		proc = rt.exec(commands);
	

	BufferedReader stdInput = new BufferedReader(new 
	     InputStreamReader(proc.getInputStream()));

	BufferedReader stdError = new BufferedReader(new 
	     InputStreamReader(proc.getErrorStream()));

	// read the output from the command
	System.out.println("Here is the standard output of the command:\n");
	String s = null;
	while ((s = stdInput.readLine()) != null) {
	    System.out.println(s);
	}

	// read any errors from the attempted command
	System.out.println("Here is the standard error of the command (if any):\n");
	while ((s = stdError.readLine()) != null) {
	    System.out.println(s);
	}
	}
}
