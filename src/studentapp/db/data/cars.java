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
 @DatabaseTable(tableName = "cars")
public class cars {
     @DatabaseField(generatedId = true)
       private int id;
     @DatabaseField
      private String car_name;
     @DatabaseField
      private String car_num;
     
      public cars(String car_name,String car_num) {
        this.car_name = car_name;
        this.car_num = car_num;
    }
    public cars() {
        
    }
     public int getId() {
        return id;
    }
      public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    } 
      public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    } 
    
}
