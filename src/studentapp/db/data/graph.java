/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.db.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author Алексей
 */
@DatabaseTable(tableName = "graph")
public class graph {
    
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField 
    private String data;

   
    @DatabaseField
    private String master;
    @DatabaseField
    private String student;
    @DatabaseField
    private String tema;
    @DatabaseField
    private String tema_time;
     @DatabaseField
    private Integer group;
     @DatabaseField
     private String time_pr;
     @DatabaseField
      private String time_min;
     
    public graph() {
    }
    
     public graph(String data, String master, String student, String tema, String tema_time, Integer group,String time_pr,String time_min) {
        this.data = data;
        this.master = master;
        this.student = student;
        this.tema = tema;
        this.tema_time = tema_time;
        this.group = group;
        this.time_pr = time_pr;
        this.time_min = time_min;
    }
     public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getMaster() {
        return master;
    }

    public String getStudent() {
        return student;
    }

    public String getTema() {
        return tema;
    }

    public String getTema_time() {
        return tema_time;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setTema_time(String tema_time) {
        this.tema_time = tema_time;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getTime_pr() {
        return time_pr;
    }

    public String getTime_min() {
        return time_min;
    }

    public void setTime_pr(String time_pr) {
        this.time_pr = time_pr;
    }

    public void setTime_min(String time_min) {
        this.time_min = time_min;
    }
     
   
    
    
}
