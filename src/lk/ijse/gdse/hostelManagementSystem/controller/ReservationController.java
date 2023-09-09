package lk.ijse.gdse.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostelManagementSystem.bo.BOFactory;
import lk.ijse.gdse.hostelManagementSystem.bo.custom.ReservationBO;
import lk.ijse.gdse.hostelManagementSystem.dto.CustomDTO;
import lk.ijse.gdse.hostelManagementSystem.dto.ReservationDTO;
import lk.ijse.gdse.hostelManagementSystem.dto.RoomDTO;
import lk.ijse.gdse.hostelManagementSystem.dto.StudentDTO;
import lk.ijse.gdse.hostelManagementSystem.entity.Room;
import lk.ijse.gdse.hostelManagementSystem.entity.Student;
import lk.ijse.gdse.hostelManagementSystem.util.Navigation;
import lk.ijse.gdse.hostelManagementSystem.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ReservationController {
    public JFXTextField txtSearchReservation;
    public TableView<CustomDTO> tblReservation;
    public TableColumn colResID;
    public TableColumn colResDate;
    public TableColumn colRoomTypeIDRes;
    public TableColumn colRoomTypeRes;
    public TableColumn colStudentIDRes;
    public TableColumn colStdNameRes;
    public TableColumn colKeyMoneyRes;
    public TableColumn colPaymentStatus;
    public JFXButton btnNew;
    public JFXButton btnEdit;
    public RadioButton rbAll;
    public ToggleGroup FilterPayment;
    public RadioButton rbPending;
    public RadioButton rbPaid;
    public JFXTextField txtSearchRoom;
    public TableView<RoomDTO>tblRooms;
    public TableColumn colRoomTypeID;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    public JFXTextField txtSearchStudent;
    public TableView<StudentDTO> tblStudent;
    public TableColumn colStdID;
    public TableColumn colStdName;
    public JFXTextField txtStudentID;
    public RadioButton rbPendingStatus;
    public ToggleGroup PaymentStatus;
    public RadioButton rbPaidStatus;
    public JFXTextField txtResID;
    public JFXTextField txtRoomID;
    public JFXButton btnDelete;
    public JFXButton btnCancel;
    public JFXButton btnReserve;
    public AnchorPane pane;
    public AnchorPane reservationDetailPane;
    public AnchorPane newReservationPane;
    public DatePicker txtDate;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.Type.RESERVATION);

    StudentDTO studentDTO;
    RoomDTO roomDTO;

    public void initialize(){
        newReservationPane.setDisable(true);
        reservationDetailPane.setDisable(false);
        txtResID.setEditable(false);
        txtRoomID.setEditable(false);
        txtStudentID.setEditable(false);

        // room table////////////////////////////////////////////
        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        txtSearchRoom.textProperty().addListener((observable, oldValue, newValue) -> {
            loadRoomTable(newValue);
        });

        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtRoomID.setText(newValue.getRoom_type_id());
                roomDTO = newValue;
            }
        });

        // student table///////////////////////////////////////
        colStdID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStdName.setCellValueFactory(new PropertyValueFactory<>("name"));

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtStudentID.setText(newValue.getId());
                studentDTO = newValue;
            }
        });

        txtSearchStudent.textProperty().addListener((observable, oldValue, newValue) -> {
            loadStudentTable(newValue);
        });

        // reservation table//////////////////////////////////////////////////////////////
        colResID.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        colResDate.setCellValueFactory(new PropertyValueFactory<>("res_date"));
        colRoomTypeIDRes.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colRoomTypeRes.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStudentIDRes.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStdNameRes.setCellValueFactory(new PropertyValueFactory<>("name"));
        colKeyMoneyRes.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtResID.setText(newValue.getRes_id());
                txtSearchRoom.setText(newValue.getRoom_type_id());
                txtRoomID.setText(newValue.getRoom_type_id());
                txtSearchStudent.setText(newValue.getId());
                txtStudentID.setText(newValue.getId());
                txtDate.setValue(LocalDate.parse(newValue.getRes_date().toString()));
            }
        });

        txtSearchReservation.textProperty().addListener((observable, oldValue, newValue) -> {
            loadReservationTable(newValue);
        });

        //---Radio buttons
        FilterPayment.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
                RadioButton rb = (RadioButton) FilterPayment.getSelectedToggle();

                switch (rb.getText()) {
                    case "All":
                        loadReservationTable("");
                        break;
                    case "Paid":
                        loadReservationTable("Paid");
                        break;
                    case "Pending":
                        loadReservationTable("Pending");
                        break;
                }
            }
        }
        );

        loadRoomTable("");
        loadStudentTable("");
        loadReservationTable("");
    }

    private void loadReservationTable(String SearchID) {
        ObservableList<CustomDTO> list = FXCollections.observableArrayList();

        ArrayList<CustomDTO> customDTOS = reservationBO.getAllReservation();
        for (CustomDTO c : customDTOS) {
            if (c.getStatus().contains(SearchID) ||
                    c.getRes_id().contains(SearchID) ||
                    c.getRoom_type_id().contains(SearchID) ||
                    c.getType().contains(SearchID)) {

                CustomDTO customDTO = new CustomDTO(
                        c.getRes_id(),
                        c.getRes_date(),
                        c.getRoom_type_id(),
                        c.getType(),
                        c.getId(),
                        c.getName(),
                        c.getKey_money(),
                        c.getStatus());

                list.add(customDTO);
            }
        }
        tblReservation.setItems(list);
    }

    private void loadStudentTable(String SearchID) {
        ObservableList<StudentDTO> list = FXCollections.observableArrayList();

        ArrayList<StudentDTO> studentDTOS = reservationBO.getAllStudent();
        for (StudentDTO std : studentDTOS) {
            if (std.getId().contains(SearchID) ||
                    std.getName().contains(SearchID) ||
                    std.getAddress().contains(SearchID)) {
                StudentDTO studentDTO = new StudentDTO(
                        std.getId(),
                        std.getName(),
                        std.getAddress(),
                        std.getContact_no(),
                        std.getDob(),
                        std.getGender());
                list.add(studentDTO);
            }
        }
        tblStudent.setItems(list);
    }

    private void loadRoomTable(String SearchID) {
        ObservableList<RoomDTO> list = FXCollections.observableArrayList();

        ArrayList<RoomDTO> roomsDTOS = reservationBO.getAllRoom();
        for (RoomDTO std : roomsDTOS) {
            if (std.getQty() > 0) {
                if (std.getRoom_type_id().contains(SearchID) ||
                        std.getKey_money().contains(SearchID) ||
                        std.getType().contains(SearchID)) {
                    RoomDTO roomsDTO = new RoomDTO(
                            std.getRoom_type_id(),
                            std.getType(),
                            std.getKey_money(),
                            std.getQty());
                    list.add(roomsDTO);
                }
            }
        }
        tblRooms.setItems(list);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        String nextID = generateNextID(reservationBO.getCurrentID());
        txtResID.setText(nextID);
        txtSearchRoom.setText("");
        txtRoomID.setText("");
        txtSearchStudent.setText("");
        txtStudentID.setText("");
        txtDate.setValue(LocalDate.now());

        newReservationPane.setDisable(false);
        reservationDetailPane.setDisable(true);

        btnDelete.setDisable(true);

        btnReserve.setText("Reserve");

        rbAll.setSelected(true);
    }

    private String generateNextID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("RS0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "RS0" + id;
        }
        return "RS01";
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
        newReservationPane.setDisable(false);
        reservationDetailPane.setDisable(true);
        btnReserve.setText("Update");
        btnDelete.setDisable(false);
        rbAll.setSelected(true);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            String id = txtResID.getText();

            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setRes_id(id);

            boolean isDeleted = reservationBO.deleteReservation(reservationDTO);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, " Deleted ! ").show();
                loadReservationTable("");
                loadRoomTable("");
                loadStudentTable("");

                newReservationPane.setDisable(true);
                reservationDetailPane.setDisable(false);
            } else {
                new Alert(Alert.AlertType.ERROR, " Error ! ").show();
            }
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        newReservationPane.setDisable(true);
        reservationDetailPane.setDisable(false);

        txtResID.clear();

        txtStudentID.clear();
        txtRoomID.clear();
    }

    public void btnReserveOnAction(ActionEvent actionEvent) {
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setRes_id(txtResID.getText());
        reservationDTO.setRes_date(txtDate.getValue());

        RadioButton rb = (RadioButton) PaymentStatus.getSelectedToggle();
        reservationDTO.setStatus(rb.getText());

        try {
            reservationDTO.setRoom(new Room(
                            roomDTO.getRoom_type_id(),
                            roomDTO.getType(),
                            roomDTO.getKey_money(),
                            roomDTO.getQty()));

            reservationDTO.setStudent(new Student(
                            studentDTO.getId(),
                            studentDTO.getName(),
                            studentDTO.getAddress(),
                            studentDTO.getContact_no(),
                            studentDTO.getDob(),
                            studentDTO.getGender()));
        } catch (Exception ex) {
            new Alert(Alert.AlertType.WARNING, " Select / Fill Data ! ").show();
        }

        if (txtDate.getValue() != null) {
            if (btnReserve.getText().equals("Reserve")) {
                boolean isAdded = reservationBO.addReservation(reservationDTO);
                if (isAdded) {
                    new Alert(Alert.AlertType.INFORMATION, " Added ! ").show();

                    loadReservationTable("");
                    loadRoomTable("");
                    loadStudentTable("");

                    newReservationPane.setDisable(true);
                    reservationDetailPane.setDisable(false);

                } else {
                    new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                }
            } else {
                boolean isAdded = reservationBO.updateReservation(reservationDTO);
                if (isAdded) {
                    new Alert(Alert.AlertType.INFORMATION, " Updated ! ").show();

                    loadReservationTable("");
                    loadRoomTable("");
                    loadStudentTable("");

                    newReservationPane.setDisable(true);
                    reservationDetailPane.setDisable(false);

                } else {
                    new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, " Wrong Date ! ").show();
        }
    }
}
