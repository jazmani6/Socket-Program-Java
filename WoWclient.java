import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WoWclient {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: Wowclient <hostname> <port>");
            return;
        }
        int port = Integer.parseInt(args[1]);
        try {
            byte[] receivePacket = new byte[1024]; // Arbitrary value just to hold a set of recieved packets in memory
            InetAddress IPaddress = InetAddress.getByName(args[0]);
            DatagramSocket clientsocket = new DatagramSocket(); // created to send the request to the clientServer lateron for 1 byte of data
            // from the WoW.txt, across the specified IP and port

            while (true) {

                DatagramPacket WoWrequest = new DatagramPacket(new byte[1], 1, IPaddress, port);
                clientsocket.send(WoWrequest);

                 // Arbitrary value just to hold a set of recieved packets in memory
                DatagramPacket response = new DatagramPacket(receivePacket, receivePacket.length);
                clientsocket.receive(response); // recieves the data from the clientSocket (WoW.txt), and sends to []receivePacket

                String WoWmessage = new String(response.getData()); //Parses the returned response data into a string value

                System.out.println(WoWmessage);

                TimeUnit.SECONDS.sleep(5); // sleep the program for 5 seconds
            }
        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
