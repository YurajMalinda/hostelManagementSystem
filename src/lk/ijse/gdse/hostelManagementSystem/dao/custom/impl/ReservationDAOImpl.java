package lk.ijse.gdse.hostelManagementSystem.dao.custom.impl;

import lk.ijse.gdse.hostelManagementSystem.bo.custom.ReservationBO;
import lk.ijse.gdse.hostelManagementSystem.dao.custom.ReservationDAO;
import lk.ijse.gdse.hostelManagementSystem.entity.Reservation;

import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public ArrayList<Reservation> getAll() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean add(Reservation entity) {
        return false;
    }

    @Override
    public boolean update(Reservation entity) {
        return false;
    }

    @Override
    public String getCurrentId() {
        return null;
    }
}
