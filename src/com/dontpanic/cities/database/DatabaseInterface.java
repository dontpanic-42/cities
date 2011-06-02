package com.dontpanic.cities.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInterface {
	
	public static void load() {
		Connection conn = establishConnection();
		freeConnection(conn);
	}
	
	public static void save() {
		Connection conn = establishConnection();
		freeConnection(conn);
	}
	
	/**
	 * Opens a connection to the database.
	 * Path: plugins/Cities/cities.sqlite
	 */
	private static Connection establishConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:plugins" + File.separator + "Cities" + File.separator + "cities.sqlite");
            conn.setAutoCommit(false);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * Closes a connection to the database.
	 */
	private static void freeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
