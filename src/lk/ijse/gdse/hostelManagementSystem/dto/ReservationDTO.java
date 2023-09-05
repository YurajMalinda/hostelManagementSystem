package lk.ijse.gdse.hostelManagementSystem.dto;

import lk.ijse.gdse.hostelManagementSystem.entity.Room;
import lk.ijse.gdse.hostelManagementSystem.entity.Student;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationDTO {
    private String res_id;
    private LocalDate res_date;
    private String status;

    private Student student;
    private Room room;
}
