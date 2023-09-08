package lk.ijse.gdse.hostelManagementSystem.util;

import animatefx.animation.FadeIn;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes routes, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (routes) {
            case LOGIN:
                window.setTitle("D24 Hostel Management System | Login");
                initUI("Login.fxml");
                break;
            case DASHBOARD:
                window.setTitle("D24 Hostel Management System | Dashboard");
                initUI("Dashboard.fxml");
                break;
            case STUDENT:
                window.setTitle("D24 Hostel Management System | Students");
                initUI("Student_Manage.fxml");
                break;
            case ROOMS:
                window.setTitle("D24 Hostel Management System | Rooms");
                initUI("Room_Manage.fxml");
                break;
            case RESERVATION:
                window.setTitle("D24 Hostel Management System | Reservations");
                initUI("Reservation_Manage.fxml");
                break;
            case CHANGE_PASSWORD:
                window.setTitle("D24 Hostel Management System | Change details");
                initUI("Change_Password.fxml");
                break;
        }

    }

    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren()
                .add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/gdse/hostelManagementSystem/view/"+location)));
        new FadeIn(pane).setSpeed(3).play();

    }



}
