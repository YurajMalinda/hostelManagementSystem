package lk.ijse.gdse.hostelManagementSystem.controller;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostelManagementSystem.bo.BOFactory;
import lk.ijse.gdse.hostelManagementSystem.bo.custom.StudentBO;
import lk.ijse.gdse.hostelManagementSystem.dto.StudentDTO;
import lk.ijse.gdse.hostelManagementSystem.util.Navigation;
import lk.ijse.gdse.hostelManagementSystem.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.Type.STUDENT);

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

        ArrayList<StudentDTO> studentDTOS = studentBO.getAllStudent();
        for (StudentDTO std : studentDTOS){
            if(std.getId().contains(searchId) || std.getName().contains(searchId) || std.getAddress().contains(searchId)){
                StudentDTO studentDTO = new StudentDTO(std.getId(), std.getName(), std.getAddress(), std.getContact_no(), std.getDob(), std.getGender());
                List.add(studentDTO);
            }
        }
        tblStudent.setItems(List);
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
        if (!txtID.getText().equals("")) {
            btnDelete.setDisable(false);
            btnCancel.setDisable(false);
            btnSave.setDisable(false);
            btnSave.setText("Update");
            txtID.setEditable(true);
            txtName.setEditable(true);
            txtAddress.setEditable(true);
            txtContact.setEditable(true);
            txtDOB.setEditable(true);
        } else {
            new Alert(Alert.AlertType.ERROR, "Not selected !").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            String studentId = txtID.getText();

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(studentId);

            Boolean isAdded = studentBO.deleteStudent(studentDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, " Student Deleted ! ").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, " Error ! ").show();
            }
        }

        loadStudentData("");
    }

    private void clearFields() {
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDOB.setValue(LocalDate.parse("2000-01-01"));
        rbMale.setSelected(true);
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        txtID.setEditable(true);
        txtName.setEditable(true);
        txtAddress.setEditable(true);
        txtContact.setEditable(true);
        txtDOB.setEditable(true);
        clearFields();

        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setText("Save");
        String nextID = generateNextID(studentBO.getCurrentID());
        txtID.setText(nextID);
        txtName.requestFocus();
    }

    private String generateNextID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("S0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "S0" + id;
        }
        return "S01";
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(!txtName.getText().equals("") || txtID.getText().equals("") || txtContact.getText().equals("")){
            String id = txtID.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String contact = txtContact.getText();
            String dob = txtDOB.getValue().toString();
            RadioButton rb = (RadioButton) gender.getSelectedToggle();
            String gender = rb.getText();

            if(isValidName() && isValidAddress() && isValidContact()){
                if(btnSave.getText().equals("Save")){
                    StudentDTO studentDTO = new StudentDTO(id, name, address, contact, dob, gender);
                    Boolean isAdded = studentBO.addStudent(studentDTO);

                    if (isAdded) {
                        new Alert(Alert.AlertType.INFORMATION, " Student Added ! ").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                    }
                }
                if(btnSave.getText().equals("Update")){
                    StudentDTO studentDTO = new StudentDTO(id, name, address, contact, dob, gender);
                    Boolean isUpdated = studentBO.updateStudent(studentDTO);

                    if (isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, " Student Updated ! ").show();
                        clearFields();
                    } else {
                        new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                    }
                }
                loadStudentData("");
            }else {
                new Alert(Alert.AlertType.WARNING, "Fill data !").show();
            }
        }
    }

    private boolean isValidContact() {
        Pattern pattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        Matcher matcher = pattern.matcher(txtContact.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtContact);
            txtContact.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidAddress() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{3,}$");
        Matcher matcher = pattern.matcher(txtAddress.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtAddress);
            txtAddress.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidName() {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3,}$");
        Matcher matcher = pattern.matcher(txtName.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtName);
            txtName.requestFocus();
            shakeUserName.play();
            return false;
        }
    }
}
