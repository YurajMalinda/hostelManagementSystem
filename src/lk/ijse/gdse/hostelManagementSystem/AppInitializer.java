package lk.ijse.gdse.hostelManagementSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse/hostelManagementSystem/view/Login.fxml"))));
        stage.show();
        stage.setResizable(false);
        stage.setY(0);
        stage.setTitle("D24 Hostel Management System | Login");
    }
}
