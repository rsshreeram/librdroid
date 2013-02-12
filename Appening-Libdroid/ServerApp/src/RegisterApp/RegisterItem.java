/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RegisterApp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.SocketException;


import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author shreeram
 */
public class RegisterItem {

    public RegisterItem() {
        
    }
    
    public static void main(String[] args) {
        try {
            new RegisterItem().registerItem();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Boolean registerItem() throws IOException {
        
        try {
            // TODO code application logic here
            registerSocket = new DatagramSocket(9799);
        } catch (SocketException ex) {
            ex.printStackTrace();
        }


        while (true) {
            registerPacket = new DatagramPacket(buffer, buffer.length);
            try {
                registerSocket.receive(registerPacket);
                msg = new  String(buffer);
                IsbnUname = msg.split(":");
                System.out.println("Registering");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            System.out.println("ISBN:" + IsbnUname[0] + " Uname:"
                    + IsbnUname[1]);
            
            String requestUrl = "http://xisbn.worldcat.org/webservices/xid/isbn/"
                    + IsbnUname[0] +"?method=getMetadata&format=json&fl=*";


            URL url;
            InputStreamReader in = null;
            Boolean result = false;

            try {
                url = new URL(requestUrl.toString());
                 in = new InputStreamReader(url.openStream());
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
           
            System.out.println("Resolving ...");
            Book detailedBook = new JsonResolve(in, IsbnUname[0],
                    IsbnUname[1]).IsbnResolve();

            if(detailedBook == null) {
                System.out.println("Failed to  resolve ISBN/ ISBN not found");
            } else {

            result =
                    new ServerConnection().registerItem(detailedBook.getTitle(),
                    detailedBook.getIsbn(), detailedBook.getAuthor(),
                    detailedBook.getPublisher(), detailedBook.getLanguage(),
                    detailedBook.getUserName());
            }

            if (result == true) {
                System.out.println("Successfully registered");
                res = "success";
            } else {
                res = "fail";
            }

            byte[] buf = res.getBytes();
            DatagramPacket sendPkt = new DatagramPacket(buf, buf.length);
            sendPkt.setAddress(registerPacket.getAddress());
            sendPkt.setPort(registerPacket.getPort());
            try {
                registerSocket.send(sendPkt);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    DatagramSocket registerSocket = null;
    DatagramPacket registerPacket;
    final int MAX_SIZE = 1024;
    byte[] buffer = new byte[MAX_SIZE];
    String msg = null;
    String[] IsbnUname = new String[3];
    String res = null;
}
