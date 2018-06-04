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
			while (!in.ready()) {

			}
			String inputLine;
			while (in.ready()) {
				inputLine = in.readLine();
				System.out.println("received inputLine " + inputLine);
				GameHost.processMove(inputLine);
			}
			//System.out.println("Checkpoint 1");
			String parsed = moveToSend.packageToString();
			System.out.println("about to send " + parsed);
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
}