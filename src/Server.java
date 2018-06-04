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
			System.out.println("server inreadline? " + in.readLine());
			while ((inputLine = in.readLine()) != null) {
				//GameHost.processMove(inputLine);
			}
			while (moveToSend != null) {
				//out.println("0 1 7 1 5 34525543");
				String parsed = moveToSend.packageToString();
				out.println(parsed);
				moveToSend = null;
			}
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