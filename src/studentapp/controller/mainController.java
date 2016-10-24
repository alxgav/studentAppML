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
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import jxl.read.biff.BiffException;
import studentapp.common.common;
import studentapp.db.data.cars;
import studentapp.db.data.graph;
import studentapp.db.data.master;
import studentapp.db.data.trafic;
import studentapp.message.error.error;
import studentapp.raport.xls.makeXLS;

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
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import jxl.write.WriteException;
import studentapp.common.dateCalc;
import studentapp.db.data.attire;
import studentapp.raport.odt.odtx_report;

/**
 *
 * @author Алексей
 */
public class mainController implements Initializable {

    public Button addML;
    public TableView tableGraphic;
    public TableColumn tgDateColumn;
    public TableColumn tgStudentColumn;
    public TableColumn tgTemaColumn;
    public TableColumn tgTimeColumn;
    public Button buttonG;


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
    
    
    common com;
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
    
   
    
    ObservableList m = FXCollections.observableArrayList(); //masters
    ObservableList<cars> car = FXCollections.observableArrayList();//cars_table
    ObservableList car_box = FXCollections.observableArrayList(); //cars box
    ObservableList<graph> graph = FXCollections.observableArrayList(); //graph box
    ObservableList traf = FXCollections.observableArrayList(); //trafic_table
    ObservableList ATTIRE = FXCollections.observableArrayList(); //attire
    
    Tooltip button_addML = new Tooltip("Додати новий маршрутний лист");
    
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
   
    trafic t;
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
    private TableColumn<?, ?> tgNColumn;
    @FXML
    private TableColumn<?, ?> tgCommonColumn;
    
    public mainController() throws SQLException {
        this.com = new common();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try { 
            
        StringConverter sc = new StringConverter() {
            @Override
            public String toString(Object t) {
                return t == null ? null : t.toString();
            }
 
            @Override
            public Object fromString(String string) {
                return string;
            }
        };    
            //traf column
        num_mrColumn.setCellValueFactory(new PropertyValueFactory<>("number_tr"));
        num_mrColumn.setCellFactory(TextFieldTableCell.forTableColumn(sc));
        data_mrColumn.setCellValueFactory(new PropertyValueFactory<>("data_tr"));
        master_mrColumn.setCellValueFactory(new PropertyValueFactory<>("master_tr"));
        master_mrColumn.setCellFactory(TextFieldTableCell.forTableColumn(sc));
        master_mrColumn.setPrefWidth(150);
        time_mrColumn.setCellValueFactory(new PropertyValueFactory<>("time_drive"));
        group_trColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        tr_mrColumn.setCellValueFactory(new PropertyValueFactory<>("auto"));
        /// student
        SurnameColumn.setCellValueFactory(new PropertyValueFactory<>("student"));
        temaColumn.setCellValueFactory(new PropertyValueFactory<>("tema"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("tema_time"));
            setMasters();
            setCars();
            setCarsBox();
            setDateDoday();
          
           traficChanged();
        button_addML.getStyleClass().add("buttons");
        addML.setTooltip(button_addML);
            
        } catch (SQLException ex) {
            new error().errorMessage(ex.toString());
        } 
    }
   public void setMasters() throws SQLException{
            GenericRawResults<String[]> rawResults = com.masters.queryRaw("SELECT master_name from master");
             
                    for(String res[]:rawResults){
                         m.add(res[0]);
                    }
            tr_master.setItems(m); 
            masterList.setItems(m);
   }
    public void setCarsBox() throws SQLException{
            GenericRawResults<String[]> rawResults = com.cars.queryRaw("SELECT car_name from cars");
             
                    for(String res[]:rawResults){
                         car_box.add(res[0]);
                    }
            tr_car.setItems(car_box); 
          
   }
    private void setDateDoday(){
        tr_data.setValue(LocalDate.now());
        
    }
   
   public void setCars() throws SQLException{
       markaTZColumn.setCellValueFactory(new PropertyValueFactory<>("car_name"));
       numTZColumn.setCellValueFactory(new PropertyValueFactory<>("car_num")); //car_num name_car
       ID.setCellValueFactory(new PropertyValueFactory<>("id"));
       QueryBuilder<cars,String> qb = com.cars.queryBuilder();
       PreparedQuery<cars> preparedQuery = qb.prepare();
       List <cars> c = com.cars.query(preparedQuery);
       c.stream().forEach((r) -> {
           car.add(r);
        });     
//       car = (ObservableList<cars>) com.cars.query(preparedQuery);
       table_cars.setItems(car);
   }
   
   public void setStudent(LocalDate d) throws SQLException{
      // DateTimeFormatter dtf =DateTimeFormatter.ofPattern("dd.MM.yyyy");
       
       QueryBuilder<graph,String> qb = com.graph.queryBuilder();
       qb.where().eq("master",""+tr_master.getSelectionModel().getSelectedItem()).and().eq("data",com.formatter.format(d) );
       PreparedQuery<graph> preparedQuery = qb.prepare();
       List <graph> g = com.graph.query(preparedQuery);
       graph.clear();
       g.stream().forEach((r) -> {
           graph.add(r);
        });
       table_trafic_student.setItems(graph);
   }
   
   private String setTime(){
       String res = "";
       double r=0;
       for(graph d:graph){
           r = Double.valueOf(d.getTema_time())+r;
       }
       res=""+r;
       return res;
   }

    public void setTrafic() throws ParseException, SQLException{

        LocalDate d = tr_data.getValue();
        Instant instant  = d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        t = new trafic(Integer.valueOf(tr_num.getText()),
            
              Date.from(instant),
              
                Integer.valueOf(tr_group.getText().equals("")?"0":tr_group.getText()),
                setTime(),
                ""+tr_master.getSelectionModel().getSelectedItem(),
                ""+tr_car.getSelectionModel().getSelectedItem());
        traf.add(t);
        
        table_trafic_list.setItems(traf);
    }

    @FXML
    private void addMasterButtonAction(ActionEvent event) throws SQLException {
        master ma = new master();
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Додати майстра");
        dialog.setHeaderText("Додати майстра");
        dialog.setContentText("майстер");
        Optional<String> result = dialog.showAndWait();
        m.add(result.get());
        masterList.setItems(m);
        ma.setMaster_name(result.get());
        com.masters.create(ma);
    }

    @FXML
    private void deleteMasterButtonAction(ActionEvent event) throws SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Видалити запис");
        alert.setHeaderText("Видалити запис");
        alert.setContentText("Ви впевнені?");
        ButtonType buttonTypeYES = new ButtonType("ТАК");
        ButtonType buttonTypeNO = new ButtonType("НІ",ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYES){
            DeleteBuilder<master,String> deleteBuilder = com.masters.deleteBuilder();
            deleteBuilder.where().eq("master_name", masterList.getSelectionModel().getSelectedItem());
            deleteBuilder.delete();
            m.remove(masterList.getSelectionModel().getSelectedItem());
        } else {
            
        }
    }

    @FXML
    private void addTZAction(ActionEvent event) {
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Додати ТЗ");
        dialog.setHeaderText("Додати ТЗ");
        ButtonType addButtonType = new ButtonType("Додати", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField TZ = new TextField();
        TZ.setPromptText("ТЗ");
        TextField TZ_NUM = new TextField();
        TZ_NUM.setPromptText("НОМЕР ТЗ");

        grid.add(new Label("ТЗ:"), 0, 0);
        grid.add(TZ, 1, 0);
        grid.add(new Label("НОМЕР ТЗ:"), 0, 1);
        grid.add(TZ_NUM, 1, 1);
        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);
           TZ.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> TZ.requestFocus());
        dialog.setResultConverter(dialogButton -> {
        if (dialogButton == addButtonType) {
            return new Pair<>(TZ.getText(), TZ_NUM.getText());
        }
        return null;
          });

         Optional<Pair<String, String>> result = dialog.showAndWait();
         result.ifPresent(cars -> {
          //  System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
            cars ca = new cars(cars.getKey(),cars.getValue());
            car.add(ca);
            try {
                com.cars.create(ca);
                table_cars.setItems(car);
            } catch (SQLException ex) {
                 new error().errorMessage(ex.toString());
            }
        });
    
        }

    @FXML
    private void deleteTZAction(ActionEvent event) throws SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Видалити запис");
        alert.setHeaderText("Видалити запис");
        alert.setContentText("Ви впевнені?");
        ButtonType buttonTypeYES = new ButtonType("ТАК");
        ButtonType buttonTypeNO = new ButtonType("НІ",ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYES){
            DeleteBuilder<cars,String> deleteBuilder = com.cars.deleteBuilder();
            cars c = table_cars.getSelectionModel().getSelectedItem();
            deleteBuilder.where().eq("car_num",c.getCar_num());
            deleteBuilder.delete();
            car.remove(c);
        
        } else {
            
        }  
    }

    @FXML
    private void testbuttonAction(ActionEvent event) throws IOException, BiffException, ParseException, SQLException {
        
       
      
        new makeXLS().setGraphic(""+tr_master.getSelectionModel().getSelectedItem(),
                Integer.valueOf(tr_time_begin.getText()),
                Integer.valueOf(tr_time_end.getText()),
                Integer.valueOf(tr_group.getText()));
    }

    @FXML
    private void tr_dataAction(ActionEvent event) throws SQLException {
       
       LocalDate d = tr_data.getValue();
       Instant instant  = d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
       QueryBuilder<trafic,String> qb = com.trafic.queryBuilder();
       qb.where().eq("data_tr",Date.from(instant));
       PreparedQuery<trafic> preparedQuery = qb.prepare();
       List <trafic> g = com.trafic.query(preparedQuery);
       traf.clear();
       g.stream().forEach((r) -> {
           traf.add(r);
        
        });
       
        table_trafic_list.setItems(traf);
    }

    @FXML
    public void addMLAction(ActionEvent actionEvent) throws ParseException, SQLException {

        setStudent(tr_data.getValue());
        setTrafic();
        com.trafic.create(t);
        tr_num.setText(Integer.valueOf(tr_num.getText())+1+"");
    }

   

    @FXML
    private void printButtonAction(ActionEvent event) throws XDocReportException, FileNotFoundException, IOException, SQLException {
        IXDocReport report = null;
        odtx_report odtx = new odtx_report();
        report = odtx.loadODT("mr");
        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
        fieldsMetadata.load("trafic", trafic.class, true);
        fieldsMetadata.load("graph", graph.class, true);
        IContext context = report.createContext();
      
      
        TableView.TableViewSelectionModel selectionModel = table_trafic_list.getSelectionModel();
        ObservableList<trafic> selectedCells = selectionModel.getSelectedItems();
        context.put("num_mr",selectedCells.get(0).getNumber_tr());
        context.put("num_group",selectedCells.get(0).getGroup());
        GenericRawResults<String[]> rawResults = com.cars.queryRaw("SELECT car_num from cars where car_name='"+""+selectedCells.get(0).getAuto()+"'");

        for(String res[]:rawResults){

            context.put("num_auto",res[0]);
        }
        Date date = selectedCells.get(0).getData_tr();
        //String time = graph.get(0).getTema_time();
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
        table_trafic_list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
               TableViewSelectionModel selectionModel = table_trafic_list.getSelectionModel();
               trafic master = (trafic)newValue;
               QueryBuilder<graph,String> qb = com.graph.queryBuilder();
                try {
                    qb.where().eq("master",master.getMaster_tr()).and().eq("data",new SimpleDateFormat("dd.MM.yyyy").format(master.getData_tr()));
                    PreparedQuery<graph> preparedQuery;
                    preparedQuery = qb.prepare();
                    List <graph> g = com.graph.query(preparedQuery);
                    graph.clear();
                g.stream().forEach((r) -> {
                    graph.add(r);
                 });
                table_trafic_student.setItems(graph);
                     } catch (SQLException ex) {
                    Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        if (result.get() == buttonTypeYES){
            DeleteBuilder<trafic,String> deleteBuilder = com.trafic.deleteBuilder();
            trafic c = table_trafic_list.getSelectionModel().getSelectedItem();
            deleteBuilder.where().eq("number_tr",c.getNumber_tr()).and().eq("data_tr", c.getData_tr());
            deleteBuilder.delete();
            traf.remove(c);
        
        } else {
            
        }  
    }

    @FXML
    private void tabelBTNAction(ActionEvent event) throws SQLException, ParseException, BiffException, IOException, WriteException {
        dateCalc d = new dateCalc();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate initial = tr_data.getValue();
        LocalDate start = initial.with(firstDayOfMonth());
        LocalDate end = initial.with(lastDayOfMonth());
        String r="";
        List<String> days = d.datesBetween(initial.with(firstDayOfMonth()), initial.with(lastDayOfMonth()));
        QueryBuilder<trafic,String> qb = com.trafic.queryBuilder();
        for(String s:days){
            Date date = format.parse(s);
           // System.out.println(sdf.format(date)); 
            
            System.out.println(new java.sql.Date(date.getTime())); // where data_tr='"+new java.sql.Date(date.getTime())+" 00:00:00.000000'
            GenericRawResults<String[]> rawResults = com.trafic.queryRaw("SELECT time_drive from trafic where data_tr='"+new java.sql.Date(date.getTime())+"'");
            List <String[]> g = rawResults.getResults();
//                    for(String res[]:rawResults){
//                        g.add(res[0]);
//                    } 
            if(g!=null){
                 String[] resultArray = g.get(0);
            r = resultArray[0];
            System.out.println(r);
            }
           
        }
     
        
    }

    @FXML
    private void addAttireDate(ActionEvent event) {
      tableDAttireColumn.setCellValueFactory(new PropertyValueFactory<>("attire_date"));
      ATTIRE.add(new attire(tr_data.getValue()));
      tableDAttire.setItems(ATTIRE);
    }

    @FXML
    private void deleteRowAttire(ActionEvent event) {
    }

    @FXML
    public void buttonGAction(ActionEvent actionEvent) {
        LocalDate d;
        List<String> columns = new ArrayList<>();
        for(int i=0;i<=10;i++){
            columns.add("data"+i);
            TableColumn col = new TableColumn(columns.get(i));
            tgCommonColumn.getColumns().addAll(col);
            //tableGraphic.getColumns().addAll(col);
        }
    }

}
