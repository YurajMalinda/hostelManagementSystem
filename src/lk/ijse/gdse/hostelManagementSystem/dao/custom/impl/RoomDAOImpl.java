package lk.ijse.gdse.hostelManagementSystem.dao.custom.impl;

import lk.ijse.gdse.hostelManagementSystem.dao.custom.RoomDAO;
import lk.ijse.gdse.hostelManagementSystem.entity.Room;
import lk.ijse.gdse.hostelManagementSystem.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public ArrayList<Room> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "SELECT * FROM room ORDER BY CAST(SUBSTRING(room_type_id, 2) AS UNSIGNED)";
        NativeQuery sqlQuery = session.createSQLQuery(sql);

        sqlQuery.addEntity(Room.class);

        List<Room> roomList = sqlQuery.list();
        ArrayList<Room> roomData = new ArrayList<>();

        for(Room room : roomList){
            roomData.add(room);
        }

        return roomData;
    }

    @Override
    public boolean delete(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Room room = session.load(Room.class, id);
            session.delete(room);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean add(Room room) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(room);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean update(Room room) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(room);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public String getCurrentId() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            String sql = "SELECT * FROM room ORDER BY CAST(SUBSTRING(room_type_id, 2) AS UNSIGNED) DESC LIMIT 1";
            NativeQuery sqlQuery = session.createSQLQuery(sql);

            sqlQuery.addEntity(Room.class);

            List <Room> roomList = sqlQuery.list();
            String id = null;
            for(Room room : roomList){
                id = room.getRoom_type_id();
            }
            transaction.commit();
            session.close();
            return id;

        }catch (Exception e){
            transaction.commit();
            session.close();
            return null;
        }
    }
}
