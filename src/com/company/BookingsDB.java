package com.company;

import java.sql.*;
import java.util.ArrayList;

public class BookingsDB {

    Connection connection = null;
    BookingsDB(){
        if(connection == null)open();
    }
    public void open(){
        try {
            String URL = "jdbc:sqlite:schoolbookings.db";
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("cannot open");
            if (connection != null) close();
        };
    }
    public void close(){
        try {
            if (connection != null) connection.close();
        } catch (SQLException e ) {
            System.out.println("cannot close");
        }
        connection = null;
    }
    public void cmdSQL (String stmt){
        if(connection == null) open();
        if(connection == null) {System.out.println("No connection"); return;}
        Statement stmtSQL = null;
        try {
            stmtSQL = connection.createStatement();
            stmtSQL.executeUpdate(stmt);
        } catch (SQLException e ) {
            System.out.println("Error in statement " + stmt);
        }
        try {
            if (stmtSQL != null) { stmtSQL.close(); }
        } catch (SQLException e ) {
            System.out.println("Error in statement "+ stmt);
        }
    }
    public ArrayList<String> querySQL (String query, String field){
        ArrayList<String> results = new ArrayList<>();
        if(connection ==null) open();
        if(connection ==null) {System.out.println("No connection"); return results;}
        Statement stmtSQL = null;
        try {
            stmtSQL = connection.createStatement();
            ResultSet resultSet = stmtSQL.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString(field);
                results.add(name); //change "name" to "resultField" ???? or something more logical
            }
        } catch (SQLException e ) {
            System.out.println("Error in statement " + query + " " + field);
        }
        try {
            if (stmtSQL != null) { stmtSQL.close(); }
        } catch (SQLException e ) {
            System.out.println("Error in statement "+ query + " " + field);
        }
        return results;
    }
}
