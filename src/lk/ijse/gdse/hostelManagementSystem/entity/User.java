package lk.ijse.gdse.hostelManagementSystem.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String password;
}

