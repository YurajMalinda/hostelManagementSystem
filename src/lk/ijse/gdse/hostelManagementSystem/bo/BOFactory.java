package lk.ijse.gdse.hostelManagementSystem.bo;

import lk.ijse.gdse.hostelManagementSystem.bo.custom.impl.ReservationBOImpl;
import lk.ijse.gdse.hostelManagementSystem.bo.custom.impl.RoomBOImpl;
import lk.ijse.gdse.hostelManagementSystem.bo.custom.impl.StudentBOImpl;
import lk.ijse.gdse.hostelManagementSystem.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum Type {
        STUDENT,
        ROOM,
        RECEPTION,
        USER
    }

    public SuperBO getBO(Type type) {
        switch (type) {
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RECEPTION:
                return new ReservationBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
