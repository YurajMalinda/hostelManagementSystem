package lk.ijse.gdse.hostelManagementSystem.bo.custom.impl;

import lk.ijse.gdse.hostelManagementSystem.bo.custom.RoomBO;
import lk.ijse.gdse.hostelManagementSystem.dao.DAOFactory;
import lk.ijse.gdse.hostelManagementSystem.dao.custom.RoomDAO;
import lk.ijse.gdse.hostelManagementSystem.dto.RoomDTO;
import lk.ijse.gdse.hostelManagementSystem.entity.Room;

import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.ROOM);

    @Override
    public ArrayList<RoomDTO> getAllRoom() {
        ArrayList<RoomDTO> RoomDTOs = new ArrayList<>();
        ArrayList<Room> roomData = roomDAO.getAll();

        for (Room r : roomData) {
            RoomDTOs.add(new RoomDTO(
                    r.getRoom_type_id(),
                    r.getType(),
                    r.getKey_money(),
                    r.getQty()));
        }
        return RoomDTOs;
    }

    @Override
    public Boolean deleteRoom(RoomDTO roomDTO) {
        return roomDAO.delete(roomDTO.getRoom_type_id());
    }

    @Override
    public Boolean addRoom(RoomDTO roomDTO) {
        Room room = new Room(
            roomDTO.getRoom_type_id(),
            roomDTO.getType(),
            roomDTO.getKey_money(),
            roomDTO.getQty()
        );
        return roomDAO.add(room);
    }

    @Override
    public Boolean updateRoom(RoomDTO roomDTO) {
        Room room = new Room(
                roomDTO.getRoom_type_id(),
                roomDTO.getType(),
                roomDTO.getKey_money(),
                roomDTO.getQty());

        return roomDAO.update(room);
    }

    @Override
    public String getCurrentID() {
        return roomDAO.getCurrentId();
    }
}
