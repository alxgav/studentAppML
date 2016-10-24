package studentapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;

/**
 * Created by Алексей on 22.12.2015.
 */
public class salaryController implements Initializable {

    @FXML
    private Button printButton;
    @FXML
    private TableView<?> tableSotr;
    @FXML
    private MenuItem addMenu;
    @FXML
    private TableColumn<?, ?> numColumn;
    @FXML
    private TableColumn<?, ?> surnameColumn;
    @FXML
    private TableColumn<?, ?> accountColumn;
    @FXML
    private TableColumn<?, ?> sumColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void printButtonAction(ActionEvent event) {
    }

    @FXML
    private void addMenuAction(ActionEvent event) {
    }


}
