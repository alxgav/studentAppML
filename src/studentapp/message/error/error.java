/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.message.error;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Алексей
 */
public class error {

    public error() {
    }
    public void errorMessage(Object message){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Недопустимая ошибка");
        alert.setContentText(message.toString());

        alert.showAndWait();
    }
    
}
