package lk.ijse.gdse.hostelManagementSystem.dao;

import lk.ijse.gdse.hostelManagementSystem.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum Types{
        STUDENT,
        ROOM,
        RESERVATION,
        JOIN_QUERY,
        USER
    }

    public SuperDAO getDAO(Types types){
        switch (types){
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case JOIN_QUERY:
                return new QueryDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
