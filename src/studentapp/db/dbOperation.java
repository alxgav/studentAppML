package studentapp.db;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import studentapp.common.common;
import studentapp.db.data.cars;
import studentapp.db.data.graph;
import studentapp.db.data.master;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class dbOperation {
    private common c =new common();
    public dbOperation(){
    }
    /*
    *  masters operation *
      add masters to list
     */
    public ObservableList<master> setMaster() throws SQLException {
        ObservableList<master> ol = FXCollections.observableArrayList();
        QueryBuilder<master, String> qb = c.masters.queryBuilder();
        PreparedQuery<master> pq = qb.prepare();
        List<master> m = c.masters.query(pq);
        m.forEach(ol::add);
        return ol;
    }
    // add new master
    public  void addMaster(String Surname,ObservableList<master> m) throws SQLException {
        master MASTER = new master();
        MASTER.setMaster_name(Surname);
        c.masters.create(MASTER);
        m.add(MASTER);
    }
    // delete master
   public void deleteMaster(Integer id) throws SQLException {
           DeleteBuilder<master,String> deleteBuilder = c.masters.deleteBuilder();
           deleteBuilder.where().eq("id", id);
           deleteBuilder.delete();
       }
    //edit master

    public  void editMaster(String Surname, String newSurname) throws SQLException {
        UpdateBuilder<master,String> ub = c.masters.updateBuilder();
        ub.where().eq("master_name",Surname);
        ub.updateColumnValue("master_name",newSurname);
        ub.update();
    }

    // select student
    public List<graph> setStudent(String master, Integer group, String data) throws SQLException {
        QueryBuilder<graph, String> qb = c.graph.queryBuilder();
        qb.where().eq("master",master).and().eq("group",group).and().eq("data",data);
        PreparedQuery<graph> pq = qb.prepare();
        List<graph> m = c.graph.query(pq);
        return m;
    }


    /*
    cars
     */
    // add cars
    public ObservableList<cars> setCars() throws SQLException {
        ObservableList<cars> ol = FXCollections.observableArrayList();
        QueryBuilder<cars, String> qb = c.cars.queryBuilder();
        PreparedQuery<cars> pq = qb.prepare();
        List<cars> m = c.cars.query(pq);
        m.forEach(ol::add);
        return ol;
    }
    // add car
    public void addCar(cars car) throws SQLException {
        c.cars.create(car);
    }
    // delete cars
    public boolean deleteCars(Integer id) throws SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Видалити запис");
        alert.setHeaderText("Видалити запис");
        alert.setContentText("Ви впевнені?");
        ButtonType buttonTypeYES = new ButtonType("ТАК");
        ButtonType buttonTypeNO = new ButtonType("НІ",ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYES){
            DeleteBuilder<cars,String> deleteBuilder = c.cars.deleteBuilder();
            deleteBuilder.where().eq("id",id);
            deleteBuilder.delete();
            return true;
        }
        return false;
    }

    /*
    other
     */
    public String getLastNumber() throws SQLException {
        GenericRawResults<String[]> rawResults = c.cars.queryRaw("SELECT number_tr from trafic order by id DESC LIMIT 1");
        String last_number = "";
        int number;
        for (String res[] : rawResults) {
            number = Integer.valueOf(res[0]) + 1;
            last_number = "" + number;
        }
        return last_number;
    }

}
