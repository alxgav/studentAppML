package studentapp.controller.retraining;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import studentapp.common.CustomDate;
import studentapp.common.common;
import studentapp.db.data.cars;
import studentapp.db.data.master;
import studentapp.db.data.student;
import studentapp.db.dbOperation;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

public class retraining  implements Initializable {

    private common com = new common();
    private student st = new student();
    private dbOperation dbo = new dbOperation();
    private ObservableList<student> studentData = FXCollections.observableArrayList();// student data
    private ObservableList<master> m = FXCollections.observableArrayList(); //masters
    private ObservableList<cars> c = FXCollections.observableArrayList(); //cars
    @FXML
    private TableView<student> pTable;
    @FXML
    private TableColumn<student, String> surnameColumn;
    @FXML
    private TableColumn<student,String> nameColumn;
    @FXML
    private TableColumn<student,String> middleColumn;
    @FXML
    private TableColumn<student,String> kategColumn;
    @FXML
    private TableColumn<student, Integer> payColumn;
    @FXML
    private TableColumn<String, CustomDate> data_bColumn;
    @FXML
    private TableColumn<String, CustomDate>  data_eColumn;
    @FXML
    private TableColumn<student, String> instruktorColumn;
    @FXML
    private TableColumn<student, String> carColumn;
    @FXML
    private TableColumn<student, Integer> dovColumn;
    @FXML
    private DatePicker dat1;
    @FXML
    private DatePicker dat2;
    @FXML
    private Button perBtn;


    public retraining(){

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setStudent();
            setEditComboColumn();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setStudent() throws SQLException {
        QueryBuilder<student,String> qb = com.student.queryBuilder();
        qb.query();
        PreparedQuery<student> pq = qb.prepare();
        List<student> student = com.student.query(pq);
        if(!studentData.isEmpty()){
            studentData.clear();
        }
        student.forEach((r)->studentData.add(r));
        pTable.setItems(studentData);
    }

    @FXML
    private void pTableKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.INSERT){
            System.out.println("hello");
        }
    }

    @FXML
    private void addPer(ActionEvent actionEvent) throws SQLException {
        Instant instant1  = dat1.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Instant instant2  = dat2.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        st = new student("","","","Категорія","instruktor","car",1000,0, Date.from(instant1),Date.from(instant2));
        studentData.add(st);
        pTable.setItems(studentData);
       // com.student.create(st);

    }
    private void setEditComboColumn() throws SQLException {
        String[] n = {"A1","B"};
        kategColumn.setCellFactory(ComboBoxTableCell.forTableColumn(n));
        kategColumn.setOnEditCommit((TableColumn.CellEditEvent<student,String> e)->{
            String newValue = e.getNewValue();
            int index = e.getTablePosition().getRow();
            student st = e.getTableView().getItems().get( index );
            st.setKateg(newValue);
        });
        m = dbo.setMaster();
        c = dbo.setCars();
        int m_size=m.size();
        String[] cars = new String[c.size()];
        String [] instr= new String[m_size];
        for(int i=0;i<=m_size-1;i++){
            instr[i]=m.get(i).getMaster_name();
        }
        for(int i=0;i<=c.size()-1;i++){
            cars[i]=c.get(i).getCar_name();
        }
        instruktorColumn.setCellFactory(ComboBoxTableCell.forTableColumn(instr));
        instruktorColumn.setOnEditCommit((TableColumn.CellEditEvent<student,String> e)->{
            student st = e.getTableView().getItems().get(e.getTablePosition().getRow());
            st.setInstruktor(e.getNewValue());
        });
        carColumn.setCellFactory(ComboBoxTableCell.forTableColumn(cars));
        carColumn.setOnEditCommit((TableColumn.CellEditEvent<student,String> e)->{
            student st = e.getTableView().getItems().get(e.getTablePosition().getRow());
            st.setCar(e.getNewValue());
        });
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameColumn.setOnEditCommit((TableColumn.CellEditEvent<student,String> e)->{
           // TablePosition<student, String> pos = e.getTablePosition();
            student st = e.getTableView().getItems().get(e.getTablePosition().getRow());
            st.setSurname(e.getNewValue());
        });
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit((TableColumn.CellEditEvent<student,String> e)->{
            // TablePosition<student, String> pos = e.getTablePosition();
            student st = e.getTableView().getItems().get(e.getTablePosition().getRow());
            st.setFirstname(e.getNewValue());
        });
        middleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        middleColumn.setOnEditCommit((TableColumn.CellEditEvent<student,String> e)->{
            // TablePosition<student, String> pos = e.getTablePosition();
            student st = e.getTableView().getItems().get(e.getTablePosition().getRow());
            st.setMiddlename(e.getNewValue());
        });



    }
}
