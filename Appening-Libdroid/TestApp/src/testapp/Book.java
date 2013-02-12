/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testapp;

/**
 *
 * @author shreeram
 */
public class Book {


	public Book(String identifier, String title, String creator, String type, String publisher, String language) {
		this.identifier = identifier;
		this.title = title;
		this.creator = creator;
		this.type = type;
		this.publisher = publisher;
		this.language = language;
	}

	String getIdentifier() {
		return this.identifier;
	}

	String getTitle() {
		return this.title;
	}

	String getCreator() {
		return this.creator;
	}

	String getType() {
		return this.type;
	}

	String getPublisher() {
		return this.publisher;
	}

	String getLanguage() {
		return this.language;
	}

	String identifier = null;
	String title = null;
	String creator = null;
	String type = null;
	String publisher = null;
	String language = null;
}

