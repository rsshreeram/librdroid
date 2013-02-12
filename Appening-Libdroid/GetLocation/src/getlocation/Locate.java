/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package getlocation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shreeram
 */
public class Locate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            new Locate().LocateArea("http://libris.kb.se/bib/7275172");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void LocateArea(String httpString) throws IOException {
        URL url = null;
        String inputLine;
        BufferedReader in = null ;

        try {
            url = new URL(httpString);
            in = new BufferedReader(new InputStreamReader(url.openStream()));

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        int StartIndex;
        int EndIndex;
        List<String> library = new ArrayList<String>();

        while ((inputLine = in.readLine()) != null) {
            if ((StartIndex = inputLine.lastIndexOf("<span class=\"libname\">")) > -1) {
                EndIndex = inputLine.lastIndexOf("<span class=\"beskrivning\">");
                String tmp = inputLine.substring(StartIndex, EndIndex);
                String libtmp = tmp.replace("<span class=\"libname\">", "");
                String aring = libtmp.replace("&aring;", "a");
                String Aring = aring.replace("&Aring;", "A");
                String auml = Aring.replace("&auml;", "a");
                String Auml = auml.replace("&Auml;", "a");
                String ouml = Auml.replace("&ouml;", "o");
                String Ouml = ouml.replace("&Ouml;", "o");
                String eacute = Ouml.replace("&eacute;", "e");
                String Eacute = eacute.replace("&Eacute;", "e");
                System.out.println(Eacute);
                library.add(Eacute);
                
            }
        }

       
    }

    
 }
