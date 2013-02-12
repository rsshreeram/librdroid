/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package getcoordinates;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shreeram
 */
public class GetCordinates {
    JsonReader reader;
    
    public GetCordinates() {
        
    }

    public GetCordinates(InputStreamReader in) {
            reader = new JsonReader(in);
    }
    /**
     * @param args the command line arguments
     */
    public String[] MainCoordinate(String qry) {
        // TODO code application logic here
        String requestUrl = "http://maps.google.com/maps/geo?q="+ qry;
        InputStreamReader in = null;
        String[] ordinate = new String[4];

        try {
            URL url = new URL(requestUrl.toString());
            in = new InputStreamReader(url.openStream());
         //   System.out.println("-----RESPONSE START-----");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            ordinates = new GetCordinates(in).GetCoOrdinates();
        } catch (IOException ex) {
            Logger.getLogger(GetCordinates.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("[" + ordinates[0] + "," + ordinates[1] + "," + ordinates[2] + "]");
        return ordinate;
    }

    public String[] GetCoOrdinates() throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
        String name = reader.nextName();
            if(name.equals("Placemark")) {
                GetArrayOrdinates(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return ordinates;
    }

    public void GetArrayOrdinates(JsonReader reader) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            GetObjectOrdinates(reader);
        }
        reader.endArray();
    }


    public void GetObjectOrdinates(JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("Point")) {
                GetPoint(reader);
            } else {
                reader.skipValue();
            }
        }

        reader.endObject();
    }

    public void GetPoint(JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("coordinates")) {
                GetfinalCoordinates(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
    }

    public void GetfinalCoordinates(JsonReader reader) throws IOException {
        reader.beginArray();
        int i = 0;
        while (reader.hasNext()) {
           ordinates[i] = reader.nextString();
           i++;
        }
        reader.endArray();
    }
    String[] ordinates = new String[4];

}

