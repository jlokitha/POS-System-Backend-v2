package lk.ijse.possystembackendv2.dto.impl;

import lk.ijse.possystembackendv2.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements SuperDTO {
    private Long id;
    private String description;
    private int quantity;
    private double price;
}
