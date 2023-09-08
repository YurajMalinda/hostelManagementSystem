package lk.ijse.gdse.hostelManagementSystem.bo.custom.impl;

import lk.ijse.gdse.hostelManagementSystem.bo.custom.ReservationBO;
import lk.ijse.gdse.hostelManagementSystem.dao.DAOFactory;
import lk.ijse.gdse.hostelManagementSystem.dao.custom.QueryDAO;
import lk.ijse.gdse.hostelManagementSystem.dao.custom.ReservationDAO;
import lk.ijse.gdse.hostelManagementSystem.dao.custom.RoomDAO;
import lk.ijse.gdse.hostelManagementSystem.dao.custom.StudentDAO;
import lk.ijse.gdse.hostelManagementSystem.dto.CustomDTO;
import lk.ijse.gdse.hostelManagementSystem.dto.ReservationDTO;
import lk.ijse.gdse.hostelManagementSystem.dto.RoomDTO;
import lk.ijse.gdse.hostelManagementSystem.dto.StudentDTO;
import lk.ijse.gdse.hostelManagementSystem.entity.CustomEntity;
import lk.ijse.gdse.hostelManagementSystem.entity.Reservation;
import lk.ijse.gdse.hostelManagementSystem.entity.Room;
import lk.ijse.gdse.hostelManagementSystem.entity.Student;

import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.RESERVATION);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.ROOM);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.STUDENT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.JOIN_QUERY);

    @Override
    public ArrayList<CustomDTO> getAllReservation() {
        ArrayList<CustomDTO> customDTOS = new ArrayList<>();
        ArrayList<CustomEntity> customEntities = queryDAO.getAll();

        for (CustomEntity c : customEntities) {
            customDTOS.add(new CustomDTO(
                    c.getRes_id(),
                    c.getRes_date(),
                    c.getRoom_type_id(),
                    c.getType(),
                    c.getId(),
                    c.getName(),
                    c.getKey_money(),
                    c.getStatus()));
        }
        return customDTOS;
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() {
        ArrayList<StudentDTO> studentDTOs = new ArrayList<>();
        ArrayList<Student> studentData = studentDAO.getAll();

        for (Student std : studentData) {
            studentDTOs.add(new StudentDTO(std.getId(),
                    std.getName(), std.getAddress(),
                    std.getContact_no(),
                    std.getDob(),
                    std.getGender()));
        }
        return studentDTOs;
    }

    @Override
    public ArrayList<RoomDTO> getAllRoom() {
        ArrayList<RoomDTO> roomDTOs = new ArrayList<>();
        ArrayList<Room> roomData = roomDAO.getAll();

        for (Room r : roomData) {
            roomDTOs.add(new RoomDTO(
                    r.getRoom_type_id(),
                    r.getType(),
                    r.getKey_money(),
                    r.getQty()));
        }
        return roomDTOs;
    }

    @Override
    public String getCurrentID() {
        return reservationDAO.getCurrentId();
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) {
        return reservationDAO.delete(reservationDTO.getRes_id());
    }

    @Override
    public boolean addReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();

        reservation.setRes_id(reservationDTO.getRes_id());
        reservation.setRes_date(reservationDTO.getRes_date().toString());
        reservation.setStatus(reservationDTO.getStatus());
        reservation.setRoom(reservationDTO.getRoom());
        reservation.setStudent(reservationDTO.getStudent());

        return reservationDAO.add(reservation);
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();

        reservation.setRes_id(reservationDTO.getRes_id());
        reservation.setRes_date(reservationDTO.getRes_date().toString());
        reservation.setStatus(reservationDTO.getStatus());
        reservation.setRoom(reservationDTO.getRoom());
        reservation.setStudent(reservationDTO.getStudent());

        return reservationDAO.update(reservation);
    }
}
