package lk.ijse.gdse.hostelManagementSystem.dao.custom.impl;

import lk.ijse.gdse.hostelManagementSystem.dao.custom.UserDAO;
import lk.ijse.gdse.hostelManagementSystem.entity.User;
import lk.ijse.gdse.hostelManagementSystem.util.SessionFactoryConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean add(User entity) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public String getCurrentId() {
        return null;
    }

    @Override
    public String getUserName(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            User user = session.get(User.class,id);
            transaction.commit();
            session.close();
            return user.getName();
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return null;
        }
    }

    @Override
    public String getPassword(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            User user = session.get(User.class,id);
            transaction.commit();
            session.close();
            return user.getPassword();
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return null;
        }
    }

    @Override
    public boolean updateUserDetails(String newUName, String newPword) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            User user = session.get(User.class,"1");
            user.setName(newUName);
            user.setPassword(newPword);
            session.update(user);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return false;
        }
    }
}
