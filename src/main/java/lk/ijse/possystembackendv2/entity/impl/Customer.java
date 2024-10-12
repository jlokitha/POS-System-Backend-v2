package lk.ijse.possystembackendv2.entity.impl;

import jakarta.persistence.Id;
import lk.ijse.possystembackendv2.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String address;
    private double salary;
}
