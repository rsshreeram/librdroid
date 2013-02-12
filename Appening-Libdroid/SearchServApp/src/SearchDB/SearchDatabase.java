/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SearchDB;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shreeram
 */
public class SearchDatabase {

    public SearchDatabase() {

    }

    public static void main(String[] args) {
        try {
            new SearchDatabase().SearchandReturn(System.out, "Java");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void SearchandReturn(OutputStream out, String query) throws IOException {
        List<Book> Books = this.Search(query);
        new JsonMarshall().writeJsonStream(out, Books);
        Iterator ite = Books.iterator();

        while (ite.hasNext()) {
            Book printBook = (Book) ite.next();
            System.out.println(printBook.getAuthor() + printBook.getIsbn() +
                    printBook.getLanguage() + printBook.getPublisher() +
                    printBook.getTitle() + printBook.getUserName());
        }
    }

    public List<Book> Search(String qry) {
        List<Book> Books = new ArrayList<Book>();

        Connection myConn = this.getConnection();
        
        if(myConn == null) {
            System.err.println("Connection Failed");
        }

        query = "select * from " + Name + "." + table + " WHERE TITLE LIKE '%" +
                 qry + "%' OR AUTHOR LIKE '%" + qry +"%' OR PUBLISHER LIKE '%" +
                 qry + "%'";

        try {
            Statement mystmt = myConn.createStatement();
            ResultSet mySet = mystmt.executeQuery(query);

            while (mySet.next()) {
                String title = mySet.getString("TITLE");
                String isbn = mySet.getString("ISBN");
                String author = mySet.getString("AUTHOR");
                String publisher = mySet.getString("PUBLISHER");
                String language = mySet.getString("LANGUAGE");
                String username = mySet.getString("USERNAME");

                Books.add(new Book(title, isbn, author, publisher,
                        language, username));
            }
            mystmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Books;
    }

    public Connection getConnection() {

        //TODO write your implementation code here:
        Connection myConn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        //DriverManager.registerDriver(org.apache.derby.jdbc.ClientDriver);

        if (this.dbms.equals("mysql")) {
            try {
                myConn = DriverManager.getConnection("jdbc:" + this.dbms +
                        "://" + this.serverName + ":" + this.portNumber +
                        "/", connectionProps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (this.dbms.equals("derby")) {

            try {
                myConn = DriverManager.getConnection("jdbc:" + this.dbms +
                        "://" + this.serverName + ":" + this.portNumber +
                        "/" + this.dbName, connectionProps);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        if (myConn == null) {
            System.err.println("Error connecting to Database");
        }

        return myConn;
    }

    String serverName = "localhost";
    String userName = "shreeram";
    String password = "chennai";
    String dbms = "derby";
    String portNumber = "1527";
    String Name = "SHREERAM";
    String dbName = "VISA";
    String table = "ITEM";
    String query = null;
}
