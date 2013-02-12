/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package registerclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author shreeram
 */
public class RegClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Boolean result = new RegClient().registerUser("shreem",
                    "sallyhsvagen", "ee", "10.250.10.152");
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public Boolean registerUser(String username, String address, String alias, 
            String ipaddress)
            throws SocketException, UnknownHostException, IOException {
        String msg = username + ":" + address + ":" + alias + ":";
        byte[] buffer = msg.getBytes();

        DatagramSocket registerSocket = new DatagramSocket(9899);
        DatagramPacket registerPacket = new DatagramPacket(buffer, buffer.length);
        InetAddress iaddr = InetAddress.getByName(ipaddress);
        registerPacket.setAddress(iaddr);
        registerPacket.setPort(9798);
        registerSocket.send(registerPacket);
        System.out.println("Waiting for registration");
        byte[] buf = new byte[25];
        DatagramPacket recvPkt = new DatagramPacket(buf, buf.length);
        registerSocket.receive(recvPkt);
        String result = new String(buf);
        if (result.contains("success")) {
            System.out.println("success");
            return true;
        } else {
            System.out.println("Fail");
            return false;
        }
    }

}
