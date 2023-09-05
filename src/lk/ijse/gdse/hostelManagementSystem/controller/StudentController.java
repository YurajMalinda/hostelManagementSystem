package lk.ijse.gdse.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostelManagementSystem.util.Navigation;
import lk.ijse.gdse.hostelManagementSystem.util.Routes;

import java.io.IOException;

public class StudentController {
    public JFXButton btnEdit;
    public JFXButton btnDelete;
    public JFXButton btnNew;
    public JFXTextField txtAddress;
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public RadioButton rbFemale;
    public ToggleGroup gender;
    public RadioButton rbMale;
    public JFXDatePicker txtDOB;
    public JFXButton btnCancel;
    public JFXButton btnSave;
    public JFXTextField txtSearch;
    public TableView tblStudent;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;
    public AnchorPane pane;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD,pane);
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }
}
