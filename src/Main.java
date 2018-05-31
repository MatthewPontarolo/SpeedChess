import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import java.net.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application {
	public static Stage mainStage;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override 
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		primaryStage.setTitle("Speed Chess");
		SpeedChess scene = new SpeedChess();
		primaryStage.setScene(new Scene(scene, 800, 600));
		primaryStage.show();


		new SimpleChatClient().go();
	}





	public class SimpleChatClient {
		BufferedReader reader;
		PrintWriter writer;
		Socket sock;

		public void go() {
		/*JFrame frame = new JFrame("Ludicrously Simple Chat Client");
		JPanel mainPanel = new JPanel();
		incoming = new JTextArea(15, 50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(qScroller);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);*/
			setUpNetworking();

			Thread readerThread = new Thread(new IncomingReader());
			readerThread.start();

		}

		private void setUpNetworking() {
			try {
				sock = new Socket("127.0.0.1", 5000);
				InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(streamReader);
				writer = new PrintWriter(sock.getOutputStream());
				System.out.println("networking established");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		public class SendButtonListener {
			public void actionPerformed(ActionEvent ev) {
				try {
					writer.println("Test"/*outgoing.getText()*/);
					writer.flush();

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				//outgoing.setText("");
				//outgoing.requestFocus();
			}
		}

		class IncomingReader implements Runnable {
			public void run() {
				String message;
				try {
					while ((message = reader.readLine()) != null) {
						System.out.println("client read " + message);
						//incoming.append(message + "\n");
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

	}


}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
