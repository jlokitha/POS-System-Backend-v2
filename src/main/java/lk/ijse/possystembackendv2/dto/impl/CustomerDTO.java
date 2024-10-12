package lk.ijse.possystembackendv2.dto.impl;

import lk.ijse.possystembackendv2.customObj.CustomerResponse;
import lk.ijse.possystembackendv2.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements SuperDTO, CustomerResponse {
    private String id;
    private String name;
    private String address;
    private double salary;
}
