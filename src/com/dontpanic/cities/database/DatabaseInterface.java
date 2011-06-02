/*
 * Cities
 * Copyright (C) 2011 dontpanic_42 <hi_manuel@web.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
