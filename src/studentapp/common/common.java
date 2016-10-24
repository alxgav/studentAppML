/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.common;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import studentapp.db.data.cars;
import studentapp.db.data.graph;
import studentapp.db.data.master;
import studentapp.db.data.trafic;
import studentapp.db.dbConnection;

/**
 *
 * @author Алексей
 */
public class common {
   public ConnectionSource con = new dbConnection().getConnection();
   public Dao<master, String> masters;
   public Dao<cars, String> cars;
   public Dao<graph, String> graph;
   public Dao<trafic,String> trafic;
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
    public common() throws SQLException {
        this.masters = DaoManager.createDao(con, master.class);
        this.cars = DaoManager.createDao(con, cars.class);
        this.graph = DaoManager.createDao(con,  graph.class);
        this.trafic = DaoManager.createDao(con,  trafic.class);
        
    }
    
    
}
