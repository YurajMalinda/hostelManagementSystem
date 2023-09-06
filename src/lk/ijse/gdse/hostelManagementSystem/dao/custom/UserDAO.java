package lk.ijse.gdse.hostelManagementSystem.dao.custom;

import lk.ijse.gdse.hostelManagementSystem.dao.CrudDAO;
import lk.ijse.gdse.hostelManagementSystem.entity.User;

public interface UserDAO extends CrudDAO<User> {
    String getUserName(String id);

    String getPassword(String id);

    boolean updateUserDetails(String newUName, String newPword);
}
