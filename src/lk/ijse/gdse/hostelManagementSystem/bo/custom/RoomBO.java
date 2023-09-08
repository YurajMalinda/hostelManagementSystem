package lk.ijse.gdse.hostelManagementSystem.bo.custom;

import lk.ijse.gdse.hostelManagementSystem.bo.SuperBO;
import lk.ijse.gdse.hostelManagementSystem.dto.RoomDTO;

import java.util.ArrayList;

public interface RoomBO extends SuperBO {
    ArrayList<RoomDTO> getAllRoom();

    Boolean deleteRoom(RoomDTO roomDTO);

    Boolean addRoom(RoomDTO roomDTO);

    Boolean updateRoom(RoomDTO roomDTO);

    String getCurrentID();
}
