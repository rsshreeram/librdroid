/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RegisterApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 *
 * @author shreeram
 */
public class ServerConnection {

    public ServerConnection() {
        
    }

     public Connection getConnection() {

        //TODO write your implementation code here:
        Connection myConn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);
        
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
                ex.printStackTrace();
            }
        }
        if (myConn == null) {
            System.out.println("Error connecting to Database");
        }

        return myConn;
    }

    public Boolean registerUser(String username, String address, String alias) {
        query = "INSERT INTO " + Name + "." + table + " VALUES ('" + username
                + "' , '" + address + "' , '" + alias + "')" ;
        Connection myConn = this.getConnection();
        Statement myStmt;
        try {
            myStmt = myConn.createStatement();
            Boolean mySet = myStmt.execute(query);
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean registerItem(String title, String isbn, String author, 
            String publisher, String language, String username) {
        query = "INSERT INTO " + Name + "." + itemtable + " VALUES ('" + title
                + "' , '" + isbn + "' , '" + author + "' , '" + publisher
                + "' , '"  + language + "' , '" + username + "')" ;
        Connection myConn = this.getConnection();
        Statement myStmt;
        try {
            myStmt = myConn.createStatement();
            Boolean mySet = myStmt.execute(query);
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    String serverName = "localhost";
    String userName = "shreeram";
    String password = "chennai";
    String dbms = "derby";
    String portNumber = "1527";
    String Name = "SHREERAM";
    String dbName = "VISA";
    String table = "REGISTER";
    String itemtable = "ITEM";
    String query = null;
}
