package lk.ijse.gdse.hostelManagementSystem.bo.custom;

import lk.ijse.gdse.hostelManagementSystem.bo.SuperBO;
import lk.ijse.gdse.hostelManagementSystem.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    ArrayList<StudentDTO> getAllStudent();

    Boolean deleteStudent(StudentDTO studentDTO);

    String getCurrentID();

    Boolean addStudent(StudentDTO studentDTO);

    Boolean updateStudent(StudentDTO studentDTO);
}
