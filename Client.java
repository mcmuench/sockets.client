package sockets.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	
	private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    
    public static void main(String[] args) throws UnknownHostException, IOException {
    	String response = "";
    	Scanner scanner = new Scanner(System.in);  // Create a Scanner object
    	
    	Client client = new Client();
	    client.startConnection("127.0.0.1", 4999);
    	
    	while ( !("Diese Zeile kenne ich nicht - good bye!".equals(response)) ) {
    		
    	    System.out.println("Enter message:");
    	    
    		// "Ich geh mit meiner Laterne", "Dort oben, da leuchten die Sterne", "hello" werden erkannt

    	    response = client.sendMessage(scanner.nextLine());
    	    System.out.println(response);	
    	}
    	
	    client.stopConnection();
    }

    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
    
    
}
