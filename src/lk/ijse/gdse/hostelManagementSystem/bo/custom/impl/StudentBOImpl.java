package lk.ijse.gdse.hostelManagementSystem.bo.custom.impl;

import lk.ijse.gdse.hostelManagementSystem.bo.custom.StudentBO;
import lk.ijse.gdse.hostelManagementSystem.dao.DAOFactory;
import lk.ijse.gdse.hostelManagementSystem.dao.custom.StudentDAO;
import lk.ijse.gdse.hostelManagementSystem.dto.StudentDTO;
import lk.ijse.gdse.hostelManagementSystem.entity.Student;

import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.STUDENT);

    @Override
    public ArrayList<StudentDTO> getAllStudent() {
        ArrayList<StudentDTO> StudentDTOs = new ArrayList<>();
        ArrayList<Student> studentData = studentDAO.getAll();

        for (Student std : studentData) {
            StudentDTOs.add(new StudentDTO(std.getStudent_id(), std.getName(), std.getAddress(), std.getContact_no(), std.getDob(), std.getGender()));
        }
        return StudentDTOs;
    }

    @Override
    public Boolean deleteStudent(StudentDTO studentDTO) {
        return studentDAO.delete(studentDTO.getId());
    }

    @Override
    public String getCurrentID() {
        return studentDAO.getCurrentId();
    }

    @Override
    public Boolean addStudent(StudentDTO studentDTO) {
        Student student = new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContact_no(), studentDTO.getDob(), studentDTO.getGender());
        return studentDAO.add(student);
    }

    @Override
    public Boolean updateStudent(StudentDTO studentDTO) {
        Student student = new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContact_no(), studentDTO.getDob(), studentDTO.getGender());
        return studentDAO.update(student);
    }
}
