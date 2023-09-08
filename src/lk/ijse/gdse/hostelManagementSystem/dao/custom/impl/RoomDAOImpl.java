package lk.ijse.gdse.hostelManagementSystem.dao.custom.impl;

import lk.ijse.gdse.hostelManagementSystem.dao.custom.RoomDAO;
import lk.ijse.gdse.hostelManagementSystem.entity.Room;

import java.util.ArrayList;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public ArrayList<Room> getAll() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean add(Room entity) {
        return false;
    }

    @Override
    public boolean update(Room entity) {
        return false;
    }

    @Override
    public String getCurrentId() {
        return null;
    }
}
