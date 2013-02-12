/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testapp;

/**
 *
 * @author shreeram
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HttpRequest {
    public static void main(String[] args) {
        HttpRequest req = new HttpRequest();
        req.Search(req.StringConcat("Java"));
    }

    public String StringConcat(String query) {
        return query.replace(" ", "+");
    }

    public Boolean Search(String queryString) {
       // String requestUrl = "http://libris.kb.se/xsearch?q="+ queryString +
         //       "&format=json&n=10";
        String requestUrl = "http://localhost:8080/SearchServerApp/?Book=" +
                queryString;
        try {
            URL url = new URL(requestUrl.toString());
            InputStreamReader in = new InputStreamReader(url.openStream());
            System.out.println("-----RESPONSE START-----");

            List<Book> Books = new ArrayList<Book>();
            JsonApp ReaderApp = new JsonApp();
            ReaderApp.readJsonStream(in, Books);

            in.close();

            Iterator ite = Books.iterator();

            while (ite.hasNext()) {
                Book nBooks = (Book) ite.next();
                System.out.println();
                System.out.println("creator:" + nBooks.getCreator());
                System.out.println("identifier:" + nBooks.getIdentifier());
                System.out.println("title:" + nBooks.getTitle());
                System.out.println("type:" + nBooks.getType());
                System.out.println("publisher:" + nBooks.getPublisher());
                System.out.println("language:" + nBooks.getLanguage());
                System.out.println();
            }

            System.out.println("-----RESPONSE END-----");

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
