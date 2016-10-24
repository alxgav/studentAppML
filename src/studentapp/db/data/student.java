package studentapp.db.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Алексей on 22.12.2015.
 */
@DatabaseTable(tableName = "student")

public class student {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private Integer trafic_id;
    @DatabaseField
    private String surname;

    public student(){

    }


    public int getId() {
        return id;
    }

    public Integer getTrafic_id() {
        return trafic_id;
    }

    public void setTrafic_id(Integer trafic_id) {
        this.trafic_id = trafic_id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
