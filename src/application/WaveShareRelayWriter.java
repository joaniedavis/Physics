package application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WaveShareRelayWriter {

	public static void setRelay(int relayNum, int on/off) {
		Runtime rt = Runtime.getRuntime();
		String command = "OFF"
		if (on/off == 1) {
			command = "ON";
		}
		String[] commands = {"sh","-c",String.format("sudo ./Relay2.sh CH%d %s", realyNum, command)};
	System.out.println(commands);

	Process proc;
	try {
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
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
