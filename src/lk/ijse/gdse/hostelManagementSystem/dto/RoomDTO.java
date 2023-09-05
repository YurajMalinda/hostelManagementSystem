package lk.ijse.gdse.hostelManagementSystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDTO {
    private String room_type_id;
    private String type;
    private String key_money;
    private int qty;
}
