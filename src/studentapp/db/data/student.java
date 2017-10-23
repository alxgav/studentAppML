package studentapp.db.data;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import studentapp.common.CustomDate;

import java.util.Date;

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
    @DatabaseField
    private String car;
    @DatabaseField
    private Integer payment;
    @DatabaseField
    private Integer numdov;
//    @DatabaseField (dataType = DataType.DATE_STRING)
//    private Date date_b;
//    @DatabaseField (dataType = DataType.DATE_STRING)
//    private Date date_e;
    @DatabaseField
    private Date date_b;
    @DatabaseField
    private Date date_e;
    public student(){

    }

    public student(String surname, String firstname, String middlename, String kateg, String instruktor, byte[] photo, String car, Integer payment, Integer numdov, Date date_b, Date date_e) {
        this.surname = surname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.kateg = kateg;
        this.instruktor = instruktor;
        this.photo = photo;
        this.car = car;
        this.payment = payment;
        this.numdov = numdov;
        this.date_b = date_b;
        this.date_e = date_e;
    }

    public student(String surname, String firstname, String middlename, String kateg, String instruktor, String car, Integer payment, Integer numdov, Date date_b, Date date_e) {
        this.surname = surname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.kateg = kateg;
        this.instruktor = instruktor;
        this.car = car;
        this.payment = payment;
        this.numdov = numdov;
        this.date_b = date_b;
        this.date_e = date_e;
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

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getNumdov() {
        return numdov;
    }

    public void setNumdov(Integer numdov) {
        this.numdov = numdov;
    }

    public Date getDate_b() {
        return new CustomDate(date_b.getTime());
    }

    public void setDate_b(Date date_b) {
        this.date_b = date_b;
    }

    public Date getDate_e() {
        return new CustomDate(date_e.getTime());
    }

    public void setDate_e(Date date_e) {
        this.date_e = date_e;
    }
}
