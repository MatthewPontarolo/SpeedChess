import java.io.*;
import java.net.*;

public class Client {

	public static Move moveToSend = null;

	public static void setup() throws IOException {
		String hostName = "128.111.43.38";
		int portNumber = 1050;

		try (
				Socket echoSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				GameHost.processMove(inputLine);
			}
			while (moveToSend != null) {
				//out.println("0 1 7 1 5 34525543");
				String parsed = moveToSend.packageToString();
				out.println(parsed);
				moveToSend = null;
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
					hostName);
			System.exit(1);
		}
	}

	public static void setMoveToSend(Move m) {
		moveToSend = m;
	}
}