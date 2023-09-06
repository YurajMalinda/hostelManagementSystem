package lk.ijse.gdse.hostelManagementSystem.bo.custom.impl;

import lk.ijse.gdse.hostelManagementSystem.bo.custom.UserBO;
import lk.ijse.gdse.hostelManagementSystem.dao.DAOFactory;
import lk.ijse.gdse.hostelManagementSystem.dao.custom.UserDAO;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.USER);

    @Override
    public String getUserName(String id) {
        return userDAO.getUserName(id);
    }

    @Override
    public String getPassword(String id) {
        return userDAO.getPassword(id);
    }

    @Override
    public boolean updateUserDetails(String newUName, String newPword) {
        return userDAO.updateUserDetails(newUName, newPword);
    }
}
