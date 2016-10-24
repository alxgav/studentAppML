/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.stage.Stage;

/**
 *
 * @author Алексей
 */
public class StudentApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        
        Scene scene = new Scene(root);
        String style_file = this.getClass().getResource("style/style.css").toExternalForm();
        scene.getStylesheets().add(style_file);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("res/img/gerb_tsou.png")));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
