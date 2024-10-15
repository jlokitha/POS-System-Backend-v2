package lk.ijse.possystembackendv2.dto.impl;

import lk.ijse.possystembackendv2.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDTO implements SuperDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private double salary;
}
