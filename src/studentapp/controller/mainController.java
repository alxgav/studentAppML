/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.controller;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import studentapp.StudentApp;
import studentapp.common.CustomDate;
import studentapp.common.common;
import studentapp.common.dateCalc;
import studentapp.db.data.*;
import studentapp.db.dbOperation;
import studentapp.message.error.error;
import studentapp.raport.odt.odtx_report;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 *
 * @author Алексей
 */
public class mainController implements Initializable {

    public Button addML;
    public TableView<String> tableGraphic;
    public TableColumn tgStudentColumn;
    public Button buttonG;
    public TableView<cars> tablecars;
    public TableColumn<cars, Integer> idCarsColumn;
    public TableColumn<cars, String> carsNameColumn;
    public ComboBox specComboBox;
    public DatePicker grDataBegin;
    public DatePicker grDataEnd;
    public Button grAddStudent;
    public TableColumn carsNumberColumn;
    public Button settingButton;


    @FXML
    private DatePicker tr_data;
    @FXML
    private TextField tr_num;
    @FXML
    private TextField tr_group;
    @FXML
    private ComboBox<master> tr_master;
    @FXML
    private TextField tr_time_end;
    @FXML
    private TextField tr_time_begin;
    @FXML
    private ComboBox<cars> tr_car;
    @FXML
    private TextField tr_dist_on;
    @FXML
    private TextField tr_dist_off;
    @FXML
    private TableView<trafic> table_trafic_list;
    @FXML
    private TableView<graph> table_trafic_student;



    @FXML
    private ListView<master> masterList;
    @FXML
    private Button addMasterButton;
    @FXML
    private Button deleteMasterButton;
    @FXML
    private Button addTZ;
    @FXML
    private Button deleteTZ;

    
    @FXML
    private TableView<cars> table_cars;
    @FXML
    private TableColumn<cars, String> markaTZColumn;
    @FXML
    private TableColumn<cars, String> numTZColumn;
     @FXML
    private TableColumn<cars, Integer> ID;


    List<String> columns;
    
    @FXML
    private Button testbutton;
    @FXML
    private TableColumn<graph, String> SurnameColumn;
    @FXML
    private TableColumn<graph, String> temaColumn;
    @FXML
    private TableColumn<graph, String> timeColumn;
    @FXML
    private TableColumn<trafic, String> num_mrColumn;
    @FXML
    private TableColumn<trafic, LocalDate> data_mrColumn;
    @FXML
    private TableColumn<trafic, String> master_mrColumn;
    @FXML
    private TableColumn<trafic, String> time_mrColumn;
    @FXML
    private TableColumn<trafic, Integer> group_trColumn;
    @FXML
    private TableColumn<trafic, String> tr_mrColumn;
    @FXML
    private Button printButton;

    private trafic t;
    @FXML
    private  MenuItem deleteTraf;
    @FXML
    private Button tabelBTN;
    @FXML
    private TableView<attire> tableDAttire;
    @FXML
    private TableColumn<attire, LocalDate> tableDAttireColumn;
    @FXML
    private TableView<?> tableDAttireAll;
    @FXML
    private TableColumn<?, ?> tableDAttireAllCol1;
    @FXML
    private TableColumn<?, ?> tableDAttireAllCol2;
    @FXML
    private TableColumn<?, ?> tableDAttireAllCol3;
    @FXML
    private MenuItem AttireDate;
    @FXML
    private MenuItem RowAttire;
    @FXML
    private TableColumn tgNColumn;
    @FXML
    private TableColumn tgCommonColumn;
    @FXML
    private TableColumn<trafic, String> tr_mrNumberColumn;



    private ObservableList<master> m = FXCollections.observableArrayList(); //masters
    private ObservableList<cars> car = FXCollections.observableArrayList();//cars_table
    private ObservableList car_box = FXCollections.observableArrayList(); //cars box
    private ObservableList<graph> graph = FXCollections.observableArrayList(); //graph box
    private ObservableList<trafic> traf = FXCollections.observableArrayList(); //trafic_table
    private ObservableList ATTIRE = FXCollections.observableArrayList(); //attire
    //  private ObservableList grData = FXCollections.observableArrayList();//grafic data
    private common com = new common();
    private dbOperation dbo = new dbOperation();
    public mainController() {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

//        StringConverter sc = new StringConverter() {
//            @Override
//            public String toString(Object t) {
//                return t == null ? null : t.toString();
//            }
//
//            @Override
//            public Object fromString(String string) {
//                return string;
//            }
//        };
//        tgNColumn.setCellFactory(TextFieldTableCell.forTableColumn(sc));

            setMaster();
            setCars();
            setCarsBox();
            setDateDoday();

           traficChanged();
            GrTableChange();

            setTraficToday();

            tr_num.setText(dbo.getLastNumber());
        } catch (SQLException ex) {
            new error().errorMessage(ex.toString());
        } 
    }


    private void setMaster() throws SQLException {
       m = dbo.setMaster();
        List<String> l = new ArrayList<>();
        m.forEach((r)->l.add(r.getMaster_name()));
        ObservableList mast = FXCollections.observableArrayList(l);
        tr_master.setItems(mast);

    }
    private void setCarsBox() throws SQLException {
            GenericRawResults<String[]> rawResults = com.cars.queryRaw("SELECT car_name from cars");

        for (String res[] : rawResults) car_box.add(res[0]);
            tr_car.setItems(car_box); 



   }
    private void setDateDoday(){
        tr_data.setValue(LocalDate.now());
        
    }

//    private String getLastNumber() throws SQLException {
//        GenericRawResults<String[]> rawResults = com.cars.queryRaw("SELECT number_tr from trafic order by id DESC LIMIT 1");
//        String last_number = "";
//        int number;
//        for (String res[] : rawResults) {
//            number = Integer.valueOf(res[0]) + 1;
//            last_number = "" + number;
//        }
//        return last_number;
//    }

    private void setCars() throws SQLException {
        car = dbo.setCars();
        tablecars.setItems(car);
   }

    private void setStudent(LocalDate d) throws SQLException {
       QueryBuilder<graph,String> qb = com.graph.queryBuilder();
       qb.where().eq("master",""+tr_master.getSelectionModel().getSelectedItem()).and().eq("data",com.formatter.format(d) );
       PreparedQuery<graph> preparedQuery = qb.prepare();
       List <graph> g = com.graph.query(preparedQuery);
       graph.clear();
        g.forEach((r) -> graph.add(r));
       table_trafic_student.setItems(graph);
   }
   
   private String setTime(){
       String res;
       double r=0;
       for (graph d : graph) r = Double.valueOf(d.getTema_time()) + r;
       res=""+r;
       return res;
   }

    private String getCar_num() throws SQLException {
        GenericRawResults<String[]> rawResults = com.cars.queryRaw("SELECT car_num from cars where car_name='" + "" + tr_car.getSelectionModel().getSelectedItem() + "'");
        String car_num = "";
        for (String res[] : rawResults) car_num = res[0];
        return car_num;
    }


    private void setTrafic() throws ParseException, SQLException {
        LocalDate d = tr_data.getValue();
        Instant instant  = d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        t = new trafic(tr_num.getText(),
            
              Date.from(instant),
              
                Integer.valueOf(tr_group.getText().equals("")?"0":tr_group.getText()),
                setTime(),
                ""+tr_master.getSelectionModel().getSelectedItem(),
                "" + tr_car.getSelectionModel().getSelectedItem(), getCar_num());
        traf.add(t);
        table_trafic_list.setItems(traf);
    }





    private void setTraficToday() throws SQLException {
        LocalDate d = tr_data.getValue();
        Instant instant = d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        QueryBuilder<trafic, String> qb = com.trafic.queryBuilder();
        qb.where().eq("data_tr", Date.from(instant));
        PreparedQuery<trafic> preparedQuery = qb.prepare();
        List<trafic> g = com.trafic.query(preparedQuery);
        if(!traf.isEmpty()){
            traf.clear();
        }
        g.forEach((r) -> traf.add(r));
        table_trafic_list.setItems(traf);
    }

    @FXML
    private void tr_dataAction() throws SQLException {

        setTraficToday();
    }

    @FXML
    public void addMLAction() throws ParseException, SQLException {

        setStudent(tr_data.getValue());
        setTrafic();
        com.trafic.create(t);
        tr_num.setText(Integer.valueOf(tr_num.getText())+1+"");
    }

   

    @FXML
    private void printButtonAction() throws XDocReportException, IOException, SQLException {
        IXDocReport report;
        odtx_report odtx = new odtx_report();
        report = odtx.loadODT("mr");
        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
        fieldsMetadata.load("trafic", trafic.class, true);
        fieldsMetadata.load("graph", graph.class, true);
        IContext context = report.createContext();


        TableViewSelectionModel<trafic> selectionModel = table_trafic_list.getSelectionModel();
        ObservableList<trafic> selectedCells = selectionModel.getSelectedItems();
        context.put("num_mr",selectedCells.get(0).getNumber_tr());
        context.put("num_group",selectedCells.get(0).getGroup());
        context.put("num_auto", selectedCells.get(0).getAuto_number());
        Date date = selectedCells.get(0).getData_tr();
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        context.put("data_full","''"+com.dtf_day.format(res)+
                "''"+ com.month[Integer.valueOf(com.dtf_month.format(res))-1]+
                " "+com.dtf_year.format(res));
        context.put("data",com.formatter.format(res) );
        context.put("trafic",selectedCells);
        context.put("graph",graph);
        OutputStream out = odtx.outODT();
        Desktop desk = Desktop.getDesktop();
        desk.open(new File("template.odt"));
        report.process(context, out);
    }
    
    // tabletrafic change listener
    private void traficChanged(){
        table_trafic_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            QueryBuilder<graph, String> qb = com.graph.queryBuilder();
            try {
                qb.where().eq("master", newValue.getMaster_tr()).and().eq("data", new CustomDate(newValue.getData_tr().getTime()));// new SimpleDateFormat("dd.MM.yyyy").format(master.getData_tr())
                PreparedQuery<graph> preparedQuery;
                preparedQuery = qb.prepare();
                List<graph> g = com.graph.query(preparedQuery);
                graph.clear();
                g.forEach((r) -> graph.add(r));
                table_trafic_student.setItems(graph);
            } catch (SQLException ex) {
                new  error().errorMessage(ex.toString());
            }
        });
    }
    /////***

    @FXML
    private void deleteTrafAction(ActionEvent event) throws SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Видалити запис");
        alert.setHeaderText("Видалити запис");
        alert.setContentText("Ви впевнені?");
        ButtonType buttonTypeYES = new ButtonType("ТАК");
        ButtonType buttonTypeNO = new ButtonType("НІ",ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYES){
            DeleteBuilder<trafic,String> deleteBuilder = com.trafic.deleteBuilder();
            trafic c = table_trafic_list.getSelectionModel().getSelectedItem();
            deleteBuilder.where().eq("number_tr",c.getNumber_tr()).and().eq("data_tr", c.getData_tr());
            deleteBuilder.delete();
            traf.remove(c);

        }
    }

    @FXML
    private void tabelBTNAction() throws SQLException, ParseException, BiffException, IOException, WriteException {
        dateCalc d = new dateCalc();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        LocalDate initial = tr_data.getValue();
        String r;
        List<String> days = d.datesBetween(initial.with(firstDayOfMonth()), initial.with(lastDayOfMonth()));
        for(String s:days){
            Date date = format.parse(s);
            GenericRawResults<String[]> rawResults = com.trafic.queryRaw("SELECT time_drive from trafic where data_tr='"+new java.sql.Date(date.getTime())+"'");
            List <String[]> g = rawResults.getResults();
            if(g!=null){
                 String[] resultArray = g.get(0);
            r = resultArray[0];
            System.out.println(r);
            }
        }
    }

    @FXML
    private void addAttireDate(ActionEvent event) {
//      tableDAttireColumn.setCellValueFactory(new PropertyValueFactory<>("attire_date"));
//      ATTIRE.add(new attire(tr_data.getValue()));
//      tableDAttire.setItems(ATTIRE);
    }

    @FXML
    private void deleteRowAttire() {
    }

    @FXML
    public void buttonGAction() {
//        StringConverter sc = new StringConverter() {
//            @Override
//            public String toString(Object t) {
//                return t == null ? null : t.toString();
//            }
//
//            @Override
//            public Object fromString(String string) {
//                return string;
//            }
//        };
//        columns = new dateCalc().datesBetween(grDataBegin.getValue(),grDataEnd.getValue());
//        for(String g: columns){
//            TableColumn col = new TableColumn(g);
//            col.setCellFactory(TextFieldTableCell.forTableColumn(sc));
//            col.setStyle("-fx-alignment: CENTER;");
//            tgCommonColumn.getColumns().add(col);
//        }
//        buttonG.setDisable(true);

    }

    public void grAddStudentAction() {

    }

    private void GrTableChange() {
        tableGraphic.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (tableGraphic.getSelectionModel().getSelectedItem() != null) {
                ObservableList selectedCells = tableGraphic.getSelectionModel().getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                Object val = tablePosition.getTableColumn().getCellData(newValue);
                System.out.println("Selected Value: " + val);
            }
        });
    }
    private StudentApp studentApp = new StudentApp();
    public void settingButtonAction() {
        studentApp.showSetting();
    }
}
