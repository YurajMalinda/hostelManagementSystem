package lk.ijse.gdse.hostelManagementSystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private String id;
    private String name;
    private String password;
}
