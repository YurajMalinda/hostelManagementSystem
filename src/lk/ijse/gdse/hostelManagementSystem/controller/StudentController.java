package lk.ijse.gdse.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostelManagementSystem.dto.StudentDTO;
import lk.ijse.gdse.hostelManagementSystem.util.Navigation;
import lk.ijse.gdse.hostelManagementSystem.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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

    public void initialize(){
        txtID.setEditable(false);

        colID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        txtID.setEditable(false);
        txtName.setEditable(false);
        txtAddress.setEditable(false);
        txtContact.setEditable(false);
        txtDOB.setEditable(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!= null) {
                setData((StudentDTO) newValue);
                btnDelete.setDisable(true);
                btnCancel.setDisable(true);
                btnSave.setDisable(true);
                btnEdit.setDisable(false);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadStudentData(newValue);
            txtID.setEditable(false);
            txtName.setEditable(false);
            txtAddress.setEditable(false);
            txtContact.setEditable(false);
            txtDOB.setEditable(false);
        });

        loadStudentData("");
    }

    private void loadStudentData(String searchId) {
        ObservableList<StudentDTO> List = FXCollections.observableArrayList();

        ArrayList<StudentDTO> studentDTOS = studentBO.getStudentData();

    }

    private void setData(StudentDTO newValue) {
        txtID.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtContact.setText(newValue.getContact_no());
        txtDOB.setValue(LocalDate.parse(newValue.getDob()));
        if (newValue.getGender().equals("Male")) {
            rbMale.setSelected(true);
        } else {
            rbFemale.setSelected(true);
        }
    }

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
