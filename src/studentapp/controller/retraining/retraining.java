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
import studentapp.db.data.master;
import studentapp.db.data.student;
import studentapp.db.dbOperation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class retraining  implements Initializable {

    private common com = new common();
    private student st = new student();
    private dbOperation dbo = new dbOperation();
    private ObservableList<student> studentData = FXCollections.observableArrayList();// student data
    private ObservableList<master> m = FXCollections.observableArrayList(); //masters

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
    private ComboBox<master> instruktorTXT;
    @FXML
    private Button addStudentBtn;
    @FXML
    private Button saveStudentBtn;

    public retraining(){

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setStudentData();
            studentChanged();
            kategTXT.setItems(FXCollections.observableArrayList(com.kateg));
            setMaster();
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
        surnameTXT.setText("");
        firstnameTXT.setText("");
        middlenameTXT.setText("");
        kategTXT.getSelectionModel().selectFirst();
        instruktorTXT.getSelectionModel().selectFirst();
    }

   private void studentChanged(){
        studentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            QueryBuilder<student, String> qb = com.student.queryBuilder();
            try {
                qb.where().eq("id",newValue.getId());
                PreparedQuery<student> preparedQuery = qb.prepare();
                List<student> st = com.student.query(preparedQuery);
                surnameTXT.setText(st.get(0).getSurname());
                firstnameTXT.setText(st.get(0).getFirstname());
                middlenameTXT.setText(st.get(0).getMiddlename());
                kategTXT.getSelectionModel().select(st.get(0).getKateg());
                String instruktor = st.get(0).getInstruktor();
              //  instruktorTXT.getSelectionModel().select(m.get(0).getId());
              //  studentData.clear();
              //  st.forEach();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
   }

    @FXML
    private void saveStudentBtnAction(ActionEvent actionEvent) throws SQLException {
       st = new student(surnameTXT.getText(),
               firstnameTXT.getText(),
               middlenameTXT.getText(),
               ""+kategTXT.getSelectionModel().getSelectedItem(),
               ""+instruktorTXT.getSelectionModel().getSelectedItem(),null);
               studentData.add(st);
               com.student.create(st);
               studentTable.setItems(studentData);

    }

    private void setMaster() throws SQLException {
        m = dbo.setMaster();
        List<String> l = new ArrayList<>();
        m.forEach((r)->l.add(r.getMaster_name()));
        ObservableList mast = FXCollections.observableArrayList(l);
        instruktorTXT.setItems(mast);



    }
}
