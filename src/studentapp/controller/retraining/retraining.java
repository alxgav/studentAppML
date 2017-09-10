package studentapp.controller.retraining;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import studentapp.common.common;
import studentapp.db.data.student;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class retraining  implements Initializable {

    private common com = new common();
    private student st = new student();
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
    @FXML
    private TableView retTable;
    @FXML
    private TableColumn dataColumn;
    @FXML
    private TableColumn themColumn;
    @FXML
    private TableColumn timeColumn;
    @FXML
    private TableColumn descrColumn;
    @FXML
    private TextField surnameTXT;
    @FXML
    private TextField firstnameTXT;
    @FXML
    private TextField middlenameTXT;
    @FXML
    private ComboBox kategTXT;
    @FXML
    private ComboBox instruktorTXT;
    @FXML
    private Button addStudentBtn;

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

    @FXML
    private void addStudentBtnAction(ActionEvent actionEvent) throws SQLException {
//        st = new student(surnameTXT.getText(),firstnameTXT.getText(), middlenameTXT.getText(),"","",null);
//       studentData.add(st);
//       studentTable.setItems(studentData);
//       com.student.create(st);
        surnameTXT.setText("");
        firstnameTXT.setText("");
        middlenameTXT.setText("");
        kategTXT.getSelectionModel().selectFirst();
        instruktorTXT.getSelectionModel().selectFirst();
    }

    private void newStudent(){
//        surnameTXT.setText("");
//        firstnameTXT.setText("");
//        middlenameTXT.setText("");
//        kategTXT.getSelectionModel().selectFirst();
//        instruktorTXT.getSelectionModel().selectFirst();
    }
}
