package lk.ijse.gdse.hostelManagementSystem.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String password;
}
