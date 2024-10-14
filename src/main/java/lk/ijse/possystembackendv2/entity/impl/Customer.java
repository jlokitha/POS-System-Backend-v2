package lk.ijse.possystembackendv2.entity.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.possystembackendv2.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer implements SuperEntity {
    @Id
    private String id;
    private String name;
    @Column(unique = true)
    private String email;
    private String address;
    private double salary;
}
