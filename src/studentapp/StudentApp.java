/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import studentapp.controller.SettingsController;

import java.awt.*;
import java.io.IOException;

/**
 *
 * @author Алексей
 */
public class StudentApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Scene scene = new Scene(root, screenSize.getWidth() - 50, screenSize.getHeight() - 50);
        String style_file = this.getClass().getResource("style/style.css").toExternalForm();
        scene.getStylesheets().add(style_file);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("res/img/gerb_tsou.png")));

        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

    }



    // show setting

    public void showSetting(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StudentApp.class.getResource("view/settings.fxml"));
        try {
            BorderPane dp = loader.load();
            Stage dialogSetting = new Stage();
            dialogSetting.setTitle("Налаштування");
            dialogSetting.initModality(Modality.APPLICATION_MODAL);
            dialogSetting.setResizable(false);
            dialogSetting.setIconified(false);
            Scene scene = new Scene(dp);
            scene.getStylesheets().add(this.getClass().getResource("style/style.css").toExternalForm());
            dialogSetting.setScene(scene);
            SettingsController controller = loader.getController();
            controller.setDialogStage(dialogSetting);
            dialogSetting.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
