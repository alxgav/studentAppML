/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.db.data;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;



/**
 *
 * @author Алексей
 */
 @DatabaseTable(tableName = "trafic")
public class trafic {

      @DatabaseField(generatedId = true)
      private int id;
      @DatabaseField
      private String number_tr;
      @DatabaseField (dataType = DataType.DATE_STRING)
      private Date data_tr;
      @DatabaseField
      private int group;
      @DatabaseField
      private String time_drive;
      @DatabaseField
      private String master_tr;
      @DatabaseField
      private String auto;
      @DatabaseField
      private String auto_number;
      
   
    
   public trafic(){
       
   }

    public trafic(String number_tr, Date data_tr, int group, String time_drive, String master_tr, String auto, String auto_number) {
          
        
        this.number_tr = number_tr;
        this.data_tr = data_tr;
        this.group = group;
        this.time_drive = time_drive;
        this.master_tr = master_tr;
        this.auto = auto;
        this.auto_number = auto_number;
    }

    public trafic(Date data_tr, String time_drive) {
        this.data_tr = data_tr;
        this.time_drive = time_drive;
    }

    public trafic(String number_tr) {
        this.number_tr = number_tr;
    }
   
   public int getId() {
        return id;
    }

    public String getNumber_tr() {
        return number_tr;
    }

    public void setNumber_tr(String number_tr) {
        this.number_tr = number_tr;
    }
   public Date getData_tr() {
       return data_tr;
    }

   public void setData_tr(Date data_tr) {
        this.data_tr = data_tr;
    } 
    

   public String getMaster_tr() {
        return master_tr;
    }

   public void setMaster_tr(String master_tr) {
        this.master_tr = master_tr;
    }



    public String getTime_drive() {
        return time_drive;
    }

    public void setTime_drive(String time_drive) {
        this.time_drive = time_drive;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
     public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getAuto() {
        return auto;
    }

    public String getAuto_number() {
        return auto_number;
    }

    public void setAuto_number(String auto_number) {
        this.auto_number = auto_number;
    }
}
