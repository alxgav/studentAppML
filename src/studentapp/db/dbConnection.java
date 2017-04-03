/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.db;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import studentapp.message.error.error;

import java.sql.SQLException;

/**
 *
 * @author Алексей
 */
public class dbConnection {
    final String databaseUrl = "jdbc:sqlite:db/studentapp.db";
    ConnectionSource con;
    public dbConnection() {
         try {
            con = new JdbcConnectionSource(databaseUrl);
        } catch (SQLException e) {
            new error().errorMessage(e);
        }
    }
    
    public ConnectionSource getConnection(){

            ((JdbcConnectionSource)con).setUsername("");
            ((JdbcConnectionSource)con).setPassword("");
            System.out.println("connected");

        return con;
    }
    
    
}
