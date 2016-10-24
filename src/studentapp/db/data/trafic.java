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
      private int number_tr;
      @DatabaseField(dataType = DataType.DATE_STRING)
      private Date data_tr;
      @DatabaseField
      private int group;
      @DatabaseField
      private String time_drive;
      @DatabaseField
      private String master_tr;
      @DatabaseField
      private String auto;
      
   
    
   public trafic(){
       
   }
   
    public trafic(int number_tr,Date data_tr,int group, String time_drive,String master_tr,String auto){
          
        
        this.number_tr = number_tr;
        this.data_tr = data_tr;
        this.group = group;
        this.time_drive = time_drive;
        this.master_tr = master_tr;
        this.auto = auto;
    }
    

    public trafic(int number_tr){
        this.number_tr = number_tr;
    }
   
   public int getId() {
        return id;
    }
   public int getNumber_tr() {
        return number_tr;
    }

   public void setNumber_tr(int number_tr) {
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
}
