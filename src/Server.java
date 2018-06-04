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

			String parsed = moveToSend.packageToString();
			out.println(parsed);
			moveToSend = null;
			SpeedChess.readyToSend = false;
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