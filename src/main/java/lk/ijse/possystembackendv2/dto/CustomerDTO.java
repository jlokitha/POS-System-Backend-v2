package lk.ijse.possystembackendv2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private int id;
    private String name;
    private String address;
    private double salary;
}
