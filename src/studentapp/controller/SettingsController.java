package studentapp.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import jxl.read.biff.BiffException;
import studentapp.db.data.cars;
import studentapp.db.data.master;
import studentapp.db.dbOperation;
import studentapp.raport.xls.makeXLS;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class SettingsController implements Initializable {

    public TableView<master> masterTable;
    public TableView<cars> carsTable;
    public TableColumn masterColumn;
    public Button addButtonMaster;
    public Button editButtonMaster;
    public Button deleteButtonMaster;
    public TextField MasterText;
    public TextField colValue;
    public TextField students;
    public ComboBox<String> mastersBox;
    public Button excelButton;
    public TextField Group;
    public Button carsAddButton;
    public Button carsdeleteButton;

    private Stage dialogStage;
    private ObservableList<master> m; //masters
    private ObservableList<cars> car; //masters
    private dbOperation dbo  = new dbOperation() ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            m = dbo.setMaster();
            setBox();
            car = dbo.setCars();
            carsTable.setItems(car);
            masterTable.setItems(m);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void addButtonMasterAction() throws SQLException {
        dbo.addMaster(MasterText.getText(),m);
    }

    public void editButtonMasterAction() throws SQLException {
        master mast =  masterTable.getSelectionModel().getSelectedItem();
        dbo.editMaster(mast.getMaster_name(),MasterText.getText());
        m.remove(mast);
        m.add(new master(MasterText.getText()));
        masterTable.refresh();
    }

    public void deleteButtonMasterAction() throws SQLException {
        master mast =  masterTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Видалити запис");
        alert.setHeaderText("Видалити запис");
        alert.setContentText("Ви впевнені?");
        ButtonType buttonTypeYES = new ButtonType("ТАК");
        ButtonType buttonTypeNO = new ButtonType("НІ", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYES){
            dbo.deleteMaster(mast.getId());
            m.remove(mast);
        }
    }

    public void excelButtonAction() throws BiffException, SQLException, ParseException, IOException {
            String colColumn = colValue.getText();
            String student = students.getText();
            String group = Group.getText();
            if (!colColumn.equals("")||!student.equals("")||!group.equals("")){
                new makeXLS().setGraphic(""+mastersBox.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(student),
                        Integer.parseInt(colColumn),
                        Integer.parseInt(group));
            }
    }

    private void setBox(){
       List<String> l = new ArrayList<>();
       m.forEach((r)->l.add(r.getMaster_name()));
       ObservableList<String> mast = FXCollections.observableArrayList(l);
       mastersBox.setItems(mast);
    }

    @FXML
    private void carsAddButtonAction() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Додати ТЗ");
        dialog.setHeaderText("Додати ТЗ");
        ButtonType addButtonType = new ButtonType("Додати", ButtonBar.ButtonData.OK_DONE);
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
        TZ.textProperty().addListener((observable, oldValue, newValue) -> addButton.setDisable(newValue.trim().isEmpty()));
        dialog.getDialogPane().setContent(grid);
        Platform.runLater(TZ::requestFocus);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Pair<>(TZ.getText(), TZ_NUM.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(cars -> {
            cars ca = new cars(cars.getKey(),cars.getValue());
            car.add(ca);
            try {
                dbo.addCar(ca);
                carsTable.setItems(car);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

    public void carsdeleteButtonAction() throws SQLException {
        cars ca = carsTable.getSelectionModel().getSelectedItem();
       if(dbo.deleteCars(ca.getId())){
           car.removeAll(ca);
       }


    }
}