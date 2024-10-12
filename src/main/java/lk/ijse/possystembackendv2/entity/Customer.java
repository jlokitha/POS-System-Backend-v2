package lk.ijse.possystembackendv2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private int id;
    private String name;
    private String address;
    private double salary;
}
