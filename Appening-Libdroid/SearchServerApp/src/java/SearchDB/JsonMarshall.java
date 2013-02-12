/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SearchDB;

import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 *
 * @author shreeram
 */
public class JsonMarshall {
    public JsonMarshall() {

    }

    public void writeJsonStream(OutputStream out, List<Book> Books) throws IOException {
     JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
     writer.setIndent("    ");
     writeXsearchStream(writer, Books);
     writer.close();
   }


    public void writeXsearchStream(JsonWriter writer, List<Book> Books) throws IOException {
        writer.beginObject();
        writer.name("xsearch");
        writeBookStream(writer, Books);
        writer.endObject();
    }

    public void writeBookStream(JsonWriter writer, List<Book> Books) throws IOException {
        writer.beginObject();
        writer.name("from").value("1");
        writer.name("to").value(Books.size());
        writer.name("records").value(Books.size());
        writer.name("list");
        writeMessagesArray(writer, Books);
        writer.endObject();
    }

   public void writeMessagesArray(JsonWriter writer, List<Book> Books) throws IOException {
     writer.beginArray();
     for (Book book : Books) {
       writeMessage(writer, book);
     }
     writer.endArray();
   }

   public void writeMessage(JsonWriter writer, Book Books) throws IOException {
     writer.beginObject();
     writer.name("identifier").value(Books.getIsbn());
     writer.name("title").value(Books.getTitle());
     writer.name("creator").value(Books.getAuthor());
     writer.name("type").value("unkown");
     writer.name("publisher").value(Books.getPublisher());
     writer.name("language").value(Books.getLanguage());
     writer.endObject();
   }
   
}
