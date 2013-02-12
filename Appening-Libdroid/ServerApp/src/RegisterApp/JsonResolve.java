/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RegisterApp;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author shreeram
 */
public class JsonResolve {
    public JsonResolve (InputStreamReader in, String Isbn, String userName) {
        reader = new JsonReader(in);
        this.ISBN = Isbn;
        this.UserName = userName;
    }

    public Book IsbnResolve() throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("stat")) {
                String stat = reader.nextString();
            } else if (name.equals("list")) {
                readListArray();
            } else {
                reader.skipValue();
            }

        }
        reader.endObject();

        return resolvedBook;
    }

    public void readListArray() throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            readBook();
        }
        reader.endArray();
    }
    
    public void readBook() throws IOException {
        String title = null;
        String author = null;
        String isbn;
        String publisher = null;
        String language = null;
        String username;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("title")) {
                title = reader.nextString();
            } else if (name.equals("author")) {
                author = reader.nextString();
            } else if (name.equals("publisher")) {
                publisher = reader.nextString();
            } else if (name.equals("lang")) {
                language = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        isbn = this.ISBN;
        username = this.UserName;

        System.out.println("title:" + title + " author:" + author + "publisher:"
                  + publisher + "lang:" + language + " isbn:" + isbn +
                  "Username:" + username);

        resolvedBook = new Book(title, isbn, author, publisher, 
                language, username);
    }



    JsonReader reader;
    String UserName;
    String ISBN;
    Book resolvedBook = null;
}
