package lk.ijse.gdse.hostelManagementSystem.dao.custom;

import lk.ijse.gdse.hostelManagementSystem.dao.CrudDAO;
import lk.ijse.gdse.hostelManagementSystem.dao.SuperDAO;
import lk.ijse.gdse.hostelManagementSystem.entity.CustomEntity;

import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> getAll();
}
