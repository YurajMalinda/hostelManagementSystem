package lk.ijse.gdse.hostelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostelManagementSystem.util.Navigation;
import lk.ijse.gdse.hostelManagementSystem.util.Routes;

import java.io.IOException;

public class DashboardController {
    public AnchorPane pane;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT,pane);
    }

    public void btnReservationsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RESERVATION,pane);
    }

    public void btnSettingsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CHANGE_PASSWORD,pane);
    }

    public void btnRoomsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOMS,pane);
    }
}
