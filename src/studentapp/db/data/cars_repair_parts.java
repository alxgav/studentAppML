package studentapp.db.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import studentapp.common.CustomDate;

import java.util.Date;


@DatabaseTable(tableName = "cars_repair_parts")
public class cars_repair_parts {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "car_id", foreign = true)
    private cars car;
    @DatabaseField
    private String parts_num;
    @DatabaseField
    private Double sum;
    @DatabaseField
    private Date data_buy;

    public cars_repair_parts(cars car, String parts_num, Double sum, Date data_buy) {
        this.car = car;
        this.parts_num = parts_num;
        this.sum = sum;
        this.data_buy = data_buy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public cars getCar() {
        return car;
    }

    public void setCar(cars car) {
        this.car = car;
    }

    public String getParts_num() {
        return parts_num;
    }

    public void setParts_num(String parts_num) {
        this.parts_num = parts_num;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Date getData_buy() {
        return new CustomDate(data_buy.getTime());
    }

    public void setData_buy(Date data_buy) {
        this.data_buy = data_buy;
    }
}
