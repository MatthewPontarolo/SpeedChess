import java.net.*;
import java.io.*;

public class Server {

	private static Move moveToSend = null;

	public static void setup() throws IOException {

		int portNumber = 1058;

		try (
				ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		) {
			String inputLine;
			System.out.println("server RIGHT BEFORE WHILE LOOP");
			while ((inputLine = in.readLine()) != null) {
				System.out.println("server inreadline? " + inputLine);
				GameHost.processMove(inputLine);
			}

			System.out.println("stop 1");
			String parsed = moveToSend.packageToString();
			System.out.println("Sending move " + parsed);
			System.out.println("stop 2");
			out.println(parsed);
			System.out.println("stop 3");
			moveToSend = null;
			System.out.println("stop 4");
			SpeedChess.readyToSend = false;
			System.out.println("stop 5");
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port "
					+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}

	public static void setMoveToSend(Move m) {
		moveToSend = m;
	}
}