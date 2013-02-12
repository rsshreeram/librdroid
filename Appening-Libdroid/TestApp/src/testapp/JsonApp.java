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
import java.util.List;

import com.google.gson.stream.JsonReader;

public class JsonApp {

        public JsonApp() {
            
        }

	public void readJsonStream(InputStreamReader in, List<Book> Books) throws IOException {
	     JsonReader reader = new JsonReader(in);
	     openXsearchStream(reader, Books);
	   }

		public void openXsearchStream(JsonReader reader, List<Book> Books) throws IOException {
                        reader.beginObject();
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals("xsearch")) {
					openBookStream(reader, Books);
				} else {
					reader.skipValue();
				}
			}
			reader.endObject();
		}

	   public void openBookStream(JsonReader reader, List<Book> Books) throws IOException {
		   reader.beginObject();
		   while (reader.hasNext()) {
			   String name = reader.nextName();
			   if (name.equals("from")) {
				   System.out.println("From" + reader.nextInt());
			   } else if (name.equals("to")) {
				   System.out.println("To" + reader.nextInt());
			   } else if (name.equals("records")) {
				   System.out.println("Total Records" + reader.nextInt());
			   } else if (name.equals("list")) {
				   readBookArray(reader, Books);
			   } else {
				   reader.skipValue();
			   }
		   }
		   reader.endObject();
	   }

	   public void readBookArray(JsonReader reader, List<Book> Books) throws IOException {
	     reader.beginArray();
	     while (reader.hasNext()) {
	       Books.add(readBook(reader));
	     }
	     reader.endArray();
	   }

	  public Book readBook(JsonReader reader) throws IOException {
		  String identifier = null;
		  String title = null;
		  String creator = null;
		  String type = null;
		  String publisher = null;
		  String language = null;

		  reader.beginObject();
		  while(reader.hasNext()) {
			String name = reader.nextName();
			  if (name.equals("identifier")) {
				identifier = reader.nextString();
			} else if (name.equals("title")) {
				title = reader.nextString();
			} else if (name.equals("creator")) {
				creator = reader.nextString();
			} else if (name.equals("type")) {
				type = reader.nextString();
			} else if (name.equals("publisher")) {
				publisher = reader.nextString();
			} else if (name.equals("language")) {
				language = reader.nextString();
			} else {
				reader.skipValue();
			}
		  }
		  reader.endObject();

		  return new Book(identifier, title, creator, type,
                          publisher, language);
	  }


}

