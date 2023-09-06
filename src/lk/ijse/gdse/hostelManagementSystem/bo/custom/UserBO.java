package lk.ijse.gdse.hostelManagementSystem.bo.custom;

import lk.ijse.gdse.hostelManagementSystem.bo.SuperBO;

public interface UserBO extends SuperBO {
    String getUserName(String id);

    String getPassword(String id);

    boolean updateUserDetails(String newUName, String newPword);
}
