package lk.ijse.gdse.hostelManagementSystem.bo.custom;

import lk.ijse.gdse.hostelManagementSystem.bo.SuperBO;
import lk.ijse.gdse.hostelManagementSystem.dto.CustomDTO;
import lk.ijse.gdse.hostelManagementSystem.dto.ReservationDTO;
import lk.ijse.gdse.hostelManagementSystem.dto.RoomDTO;
import lk.ijse.gdse.hostelManagementSystem.dto.StudentDTO;

import java.util.ArrayList;

public interface ReservationBO extends SuperBO {
    ArrayList<CustomDTO> getAllReservation();

    ArrayList<StudentDTO> getAllStudent();

    ArrayList<RoomDTO> getAllRoom();

    String getCurrentID();

    boolean deleteReservation(ReservationDTO reservationDTO);

    boolean addReservation(ReservationDTO reservationDTO);

    boolean updateReservation(ReservationDTO reservationDTO);
}
