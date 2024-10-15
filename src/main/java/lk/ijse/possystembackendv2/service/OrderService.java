package lk.ijse.possystembackendv2.service;

import lk.ijse.possystembackendv2.dto.impl.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void saveOrder(OrderDTO orderDTO);
    OrderDTO getSelectedOrder(Long id);
    List<OrderDTO> getAllOrders();
}
