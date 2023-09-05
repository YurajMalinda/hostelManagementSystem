package lk.ijse.gdse.hostelManagementSystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Student {
    @Id
    private String student_id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String contact_no;
    @Column(columnDefinition = "DATE")
    private String dob;
    private String gender;

    @OneToMany (mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservationList = new ArrayList<>();
}