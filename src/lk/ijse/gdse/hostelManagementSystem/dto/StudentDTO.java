package lk.ijse.gdse.hostelManagementSystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDTO {
    private String id;
    private String name;
    private String address;
    private String contact_no;
    private String dob;
    private String gender;
}
