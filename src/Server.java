import java.net.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Server {

	private static Move moveToSend = null;
	private static boolean isIt = false;
	private static int counter = 10;
	private static String IP;

	public static void setup() throws IOException {

		int portNumber = 1058;

		try (
				ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		) {
			while (!in.ready()) { }
			String inputLine;
			while (in.ready()) {
				inputLine = in.readLine();
				String host = InetAddress.getLocalHost().toString();
				String[] parse = host.split("/");
				IP = parse[1];

				System.out.println("IP: " + IP);
				GameHost.processMove(inputLine);
			}
			String parsed = moveToSend.packageToString();
			out.println(parsed);
			moveToSend = null;
			//System.out.println("Checkpoint 2");
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port "
					+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}

	public static void setMoveToSend(Move m) {
		moveToSend = m;
	}
	public static Move getMoveToSend() {
		return moveToSend;
	}
}
