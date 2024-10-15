package lk.ijse.possystembackendv2.dto.impl;

import lk.ijse.possystembackendv2.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO implements SuperDTO {
    private Long orderId;
    private Long itemId;
    private int quantity;
}
