/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RegisterApp;

/**
 *
 * @author shreeram
 */
public class Book {
        public Book() {
            
        }


	public Book(String title, String isbn, String author, String publisher, String language, String username) {
                this.title = title;
		this.isbn = isbn;
                this.author = author;
                this.publisher = publisher;
		this.language = language;
                this.username = username;
	}

	String getIsbn() {
		return this.isbn;
	}

	String getTitle() {
		return this.title;
	}

	String getAuthor() {
		return this.author;
	}

	String getUserName() {
		return this.username;
	}

	String getPublisher() {
		return this.publisher;
	}

	String getLanguage() {
		return this.language;
	}

	String isbn = null;
	String title = null;
	String author = null;
	String username = null;
	String publisher = null;
	String language = null;
}

