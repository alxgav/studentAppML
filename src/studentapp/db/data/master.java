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
 @DatabaseTable(tableName = "master")
 
public class master {
     
     @DatabaseField(generatedId = true)
       private int id;
     @DatabaseField
      private String master_name;

    public master() {
    }

    public master(String master_name) {
        this.master_name = master_name;
    }

    public int getId() {
        return id;
    }
      public String getMaster_name() {
        return master_name;
    }

    public void setMaster_name(String master_name) {
        this.master_name = master_name;
    }
    
    
}
