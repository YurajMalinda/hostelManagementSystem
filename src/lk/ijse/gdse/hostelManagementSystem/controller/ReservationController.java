package lk.ijse.gdse.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

public class ReservationController {
    public JFXTextField txtSearchReservation;
    public TableView tblReservation;
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
    public TableView tblRooms;
    public TableColumn colRoomTypeID;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    public JFXTextField txtSearchStudent;
    public TableView tblStudent;
    public TableColumn colStdID;
    public TableColumn colStdName;
    public JFXTextField txtStudentID;
    public JFXDatePicker dateDate;
    public RadioButton rbPendingStatus;
    public ToggleGroup PaymentStatus;
    public RadioButton rbPaidStatus;
    public JFXTextField txtResID;
    public JFXTextField txtRoomID;
    public JFXButton btnDelete;
    public JFXButton btnCancel;
    public JFXButton btnReserve;

    public void btnBackOnAction(ActionEvent actionEvent) {
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }

    public void btnReserveOnAction(ActionEvent actionEvent) {
    }
}
