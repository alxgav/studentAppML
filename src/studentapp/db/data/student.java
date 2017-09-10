package studentapp.db.data;

import com.j256.ormlite.field.DataType;
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
    private String surname;
    @DatabaseField
    private String firstname;
    @DatabaseField
    private String middlename;
    @DatabaseField
    private String kateg;
    @DatabaseField
    private String instruktor;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] photo;

    public student(){

    }

    public student(String surname, String firstname, String middlename, String kateg, String instruktor, byte[] photo) {
        this.surname = surname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.kateg = kateg;
        this.instruktor = instruktor;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getKateg() {
        return kateg;
    }

    public void setKateg(String kateg) {
        this.kateg = kateg;
    }

    public String getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(String instruktor) {
        this.instruktor = instruktor;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
