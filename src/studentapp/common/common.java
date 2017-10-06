/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.common;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import studentapp.db.data.*;
import studentapp.db.dbConnection;

import java.io.*;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Алексей
 */
public class common {
   private ConnectionSource con = new dbConnection().getConnection();
   public Dao<master, String> masters;
   public Dao<cars, String> cars;
   public Dao<graph, String> graph;
   public Dao<trafic,String> trafic;
   public Dao<student,String> student;
   public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
   public DateTimeFormatter dtf_month =DateTimeFormatter.ofPattern("MM");
   public DateTimeFormatter dtf_day =DateTimeFormatter.ofPattern("dd");
   public DateTimeFormatter dtf_year =DateTimeFormatter.ofPattern("yyyy");
   public String [] month = {"січня",
                              "лютого",
                              "березня",
                              "квітня",
                              "травня",
                              "червня",
                              "липня",
                              "серпня",
                              "вересня",
                              "жовтня",
                              "листопада",
                              "грудня"};
   public String [] kateg = {"A1","B","C", "C1"};
    public common() {
        try {
            this.masters = DaoManager.createDao(con, master.class);
            this.cars = DaoManager.createDao(con, cars.class);
            this.graph = DaoManager.createDao(con,  graph.class);
            this.trafic = DaoManager.createDao(con,  trafic.class);
            this.student = DaoManager.createDao(con, student.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }
    /// read image file
    public byte[] readFile(String file) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }
    
}
