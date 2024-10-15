package lk.ijse.possystembackendv2.dto.impl;

import lk.ijse.possystembackendv2.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements SuperDTO {
    private Long id;
    private String date;
    private double total;
    private double discount;
    private Long customerId;
    private List<OrderDetailDTO> itemList;
}
