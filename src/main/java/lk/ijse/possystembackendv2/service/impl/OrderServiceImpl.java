package lk.ijse.possystembackendv2.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendv2.dto.impl.OrderDTO;
import lk.ijse.possystembackendv2.dto.impl.OrderDetailDTO;
import lk.ijse.possystembackendv2.entity.impl.Customer;
import lk.ijse.possystembackendv2.entity.impl.Order;
import lk.ijse.possystembackendv2.entity.impl.OrderDetail;
import lk.ijse.possystembackendv2.exception.CustomerNotFountException;
import lk.ijse.possystembackendv2.exception.DataPersistFailedException;
import lk.ijse.possystembackendv2.exception.OrderNotFoundException;
import lk.ijse.possystembackendv2.repository.CustomerRepository;
import lk.ijse.possystembackendv2.repository.ItemRepository;
import lk.ijse.possystembackendv2.repository.OrderDetailRepository;
import lk.ijse.possystembackendv2.repository.OrderRepository;
import lk.ijse.possystembackendv2.service.OrderService;
import lk.ijse.possystembackendv2.utils.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final Mapping mapping;

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        Order order = mapping.toEntity(orderDTO);
        order.setDate(LocalDate.parse(orderDTO.getDate()));

        Optional<Customer> customer = customerRepository.findById(orderDTO.getCustomerId());
        if (customer.isEmpty()) throw new CustomerNotFountException("Customer not found.");
        else {
            order.setCustomer(customer.get());
            order = orderRepository.save(order);

            if (order == null) {
                throw new DataPersistFailedException("Order save failed");
            }

            for (OrderDetailDTO detailDTO : orderDTO.getItemList()) {
                Order tempOrder = order;
                itemRepository.findById(detailDTO.getItemId()).ifPresent(item -> {
                    OrderDetail detail = OrderDetail.builder()
                            .quantity(detailDTO.getQuantity())
                            .item(item)
                            .order(tempOrder)
                            .build();
                    System.out.println(detail);
                    OrderDetail savedDetail = orderDetailRepository.save(detail);
                    if (savedDetail == null) {
                        throw new DataPersistFailedException("Order detail save failed");
                    }
                    item.setQuantity(item.getQuantity() - detailDTO.getQuantity());
                    itemRepository.save(item); // Ensure item is saved after updating quantity
                });
            }
        }
    }

    @Override
    public OrderDTO getSelectedOrder(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isEmpty()) throw new OrderNotFoundException("Order not found.");
        List<OrderDetail> allByOrder = orderDetailRepository.getAllByOrderId(id);
        OrderDTO orderDTO = mapping.toDto(byId.get());
        orderDTO.setItemList(mapping.toOrderDetailDtoList(allByOrder));
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        return mapping.toOrderDtoList(allOrders);
    }
}
