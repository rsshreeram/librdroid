/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RegisterApp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author shreeram
 */
public class RegisterUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final int MAX_SIZE = 1024;
        byte[] buffer = new byte[MAX_SIZE];
        DatagramSocket registerSocket = null;
        DatagramPacket registerPacket = null;
        String value[] = new String[4];
        String result = null;

        try {
            // TODO code application logic here
            registerSocket = new DatagramSocket(9798);
        } catch (SocketException ex) {
            ex.printStackTrace();
        }


        while (true) {
            result = "";
            
            registerPacket = new DatagramPacket(buffer, buffer.length);
            try {
                registerSocket.receive(registerPacket);
                String msg = new  String(buffer);
                System.out.println(msg);
                value = msg.split(":");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            ServerConnection regServConn = new ServerConnection();

            if(regServConn.registerUser(value[0], value[1], value[2]) == true) {
                System.out.println(value[0] + " Registered Successfully");
                result = "success";
            } else {
                System.out.println(value[0] + " Registration failed");
                result = "fail";
            }

            byte[] buf = result.getBytes();
            DatagramPacket sendPkt = new DatagramPacket(buf, buf.length);
            String clientaddr = registerPacket.getAddress().toString();
            System.out.println("clientaddr:" + clientaddr);
            sendPkt.setAddress(registerPacket.getAddress());
            sendPkt.setPort(registerPacket.getPort());
            
            try {
                    registerSocket.send(sendPkt);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }
    }

}
