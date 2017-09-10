package studentapp.controller.retraining;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import studentapp.common.common;
import studentapp.db.data.student;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class retraining  implements Initializable {

    private common com = new common();
    private ObservableList<student> studentData = FXCollections.observableArrayList();// student data

    @FXML
    private TableView <student> studentTable;
    @FXML
    private TableColumn <student, Integer> idColumn;
    @FXML
    private TableColumn <student, String> surnameColumn;
    @FXML
    private TableColumn <student, String> firstnameColumn;
    @FXML
    private TableColumn <student, String> middlenameColumn;

    public retraining(){

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setStudentData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setStudentData() throws SQLException {
        QueryBuilder<student, String> qb = com.student.queryBuilder();
        qb.query();
        PreparedQuery<student> preparedQuery = qb.prepare();
        List<student> g = com.student.query(preparedQuery);
        if(!studentData.isEmpty()){
            studentData.clear();
        }
        g.forEach((r) -> studentData.add(r));
        studentTable.setItems(studentData);
    }

}
