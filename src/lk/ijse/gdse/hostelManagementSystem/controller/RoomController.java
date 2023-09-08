package lk.ijse.gdse.hostelManagementSystem.controller;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostelManagementSystem.bo.BOFactory;
import lk.ijse.gdse.hostelManagementSystem.bo.custom.RoomBO;
import lk.ijse.gdse.hostelManagementSystem.dto.RoomDTO;
import lk.ijse.gdse.hostelManagementSystem.util.Navigation;
import lk.ijse.gdse.hostelManagementSystem.util.Routes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomController {
    public JFXTextField txtSearch;
    public TableView tblRooms;
    public TableColumn colRoomTypeID;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    public JFXButton btnEdit;
    public JFXButton btnDelete;
    public JFXButton btnNew;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomTypeID;
    public JFXTextField txtType;
    public JFXTextField txtQty;
    public JFXButton btnCancel;
    public JFXButton btnSave;
    public AnchorPane pane;

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.Type.ROOM);

    public void initialize(){
        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        makeEditableTxtField(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);

        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData((RoomDTO) newValue);
                btnDelete.setDisable(true);
                btnCancel.setDisable(true);
                btnSave.setDisable(true);
                btnEdit.setDisable(false);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadRoomData(newValue);
            makeEditableTxtField(false);
        });

        loadRoomData("");
    }

    private void loadRoomData(String SearchID) {
        ObservableList<RoomDTO> list = FXCollections.observableArrayList();

        ArrayList<RoomDTO> roomDTOS = roomBO.getAllRoom();
        for (RoomDTO std : roomDTOS) {
            if (std.getRoom_type_id().contains(SearchID) ||
                    std.getKey_money().contains(SearchID) ||
                    std.getType().contains(SearchID)) {
                RoomDTO roomDTO = new RoomDTO(
                        std.getRoom_type_id(),
                        std.getType(),
                        std.getKey_money(),
                        std.getQty());
                list.add(roomDTO);
            }
        }
        tblRooms.setItems(list);
    }

    private void makeEditableTxtField(boolean b) {
        //txtRoomTypeID.setEditable(b);
        txtType.setEditable(b);
        txtKeyMoney.setEditable(b);
        txtQty.setEditable(b);
    }

    private void setData(RoomDTO newValue) {
        txtRoomTypeID.setText(newValue.getRoom_type_id());
        txtType.setText(newValue.getType());
        txtKeyMoney.setText(newValue.getKey_money());
        txtQty.setText(String.valueOf(newValue.getQty()));
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
        if (!txtRoomTypeID.getText().equals("")) {
            btnDelete.setDisable(false);
            btnCancel.setDisable(false);
            btnSave.setDisable(false);
            btnSave.setText("Update");
            makeEditableTxtField(true);

        } else {
            new Alert(Alert.AlertType.ERROR, "Not selected !").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            String idText = txtRoomTypeID.getText();

            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoom_type_id(idText);

            Boolean isAdded = roomBO.deleteRoom(roomDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, " Room Deleted ! ").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, " Error ! ").show();
            }
        }

        loadRoomData("");
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        makeEditableTxtField(true);
        clearFields();

        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setText("Save");
        //String nextID = generateNextID(roomsBO.getCurrentID());
        //txtRoomTypeID.setText(nextID);
        txtRoomTypeID.requestFocus();
        txtRoomTypeID.setText("RM-");
    }

    private void clearFields() {
        txtRoomTypeID.clear();
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!txtRoomTypeID.getText().equals("") || txtType.getText().equals("") || txtKeyMoney.getText().equals("")) {
            String roomTypeIDText = txtRoomTypeID.getText();
            String typeText = txtType.getText();
            String keyMoneyText = txtKeyMoney.getText();
            int qtyText = Integer.parseInt(txtQty.getText());

            if (isValidRoomTypeID() && isValidType() && isValidKeyMoney() && isValidQTY()) {
                if (btnSave.getText().equals("Save")) {
                    RoomDTO RoomDTO = new RoomDTO(roomTypeIDText, typeText, keyMoneyText, qtyText);
                    Boolean isAdded = roomBO.addRoom(RoomDTO);

                    if (isAdded) {
                        new Alert(Alert.AlertType.INFORMATION, " Room Added ! ").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                    }
                }

                if (btnSave.getText().equals("Update")) {
                    RoomDTO RoomDTO = new RoomDTO(roomTypeIDText, typeText, keyMoneyText, qtyText);
                    Boolean isUpdated = roomBO.updateRoom(RoomDTO);

                    if (isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, " Room Updated ! ").show();
                        clearFields();
                    } else {
                        new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                        clearFields();
                    }
                }
                loadRoomData("");

            } else {
                new Alert(Alert.AlertType.WARNING, "Fill data !").show();
            }
        }
    }

    private boolean isValidQTY() {
        Pattern pattern = Pattern.compile("^[0-9]{1,}$");
        Matcher matcher = pattern.matcher(txtQty.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtQty);
            txtQty.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidKeyMoney() {
        Pattern pattern = Pattern.compile("^[0-9]{3,}$");
        Matcher matcher = pattern.matcher(txtKeyMoney.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtKeyMoney);
            txtKeyMoney.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidType() {
        Pattern pattern = Pattern.compile("^(AC|Non-AC|Non-AC/Food|AC/Food)$");
        Matcher matcher = pattern.matcher(txtType.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtType);
            txtType.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidRoomTypeID() {
        Pattern pattern = Pattern.compile("^(?:RM-)[0-9]{4}$");
        Matcher matcher = pattern.matcher(txtRoomTypeID.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(txtRoomTypeID);
            txtRoomTypeID.requestFocus();
            shakeUserName.play();
            return false;
        }
    }
}
