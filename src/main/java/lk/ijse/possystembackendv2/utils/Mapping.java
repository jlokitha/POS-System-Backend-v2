package lk.ijse.possystembackendv2.utils;

import lk.ijse.possystembackendv2.dto.impl.CustomerDTO;
import lk.ijse.possystembackendv2.dto.impl.ItemDTO;
import lk.ijse.possystembackendv2.dto.impl.OrderDTO;
import lk.ijse.possystembackendv2.dto.impl.OrderDetailDTO;
import lk.ijse.possystembackendv2.entity.impl.Customer;
import lk.ijse.possystembackendv2.entity.impl.Item;
import lk.ijse.possystembackendv2.entity.impl.Order;
import lk.ijse.possystembackendv2.entity.impl.OrderDetail;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    // matters of customer entity and dto
    public CustomerDTO toDto(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }
    public Customer toEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }
    public List<CustomerDTO> toCustomerDtoList(List<Customer> customers) {
        return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {}.getType());
    }
    // matters of item entity and dto
    public ItemDTO toDto(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }
    public Item toEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }
    public List<ItemDTO> toItemDtoList(List<Item> items) {
        return modelMapper.map(items, new TypeToken<List<ItemDTO>>() {}.getType());
    }
    // matters of order entity and dto
    public OrderDTO toDto(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }
    public Order toEntity(OrderDTO itemDTO) {
        return modelMapper.map(itemDTO, Order.class);
    }
    public List<OrderDTO> toOrderDtoList(List<Order> orders) {
        return modelMapper.map(orders, new TypeToken<List<OrderDTO>>() {}.getType());
    }
    // matters of orderDetail entity and dto
    public List<OrderDetailDTO> toOrderDetailDtoList(List<OrderDetail> orders) {
        return modelMapper.map(orders, new TypeToken<List<OrderDetailDTO>>() {}.getType());
    }
    public List<OrderDetail> toOrderDetailList(List<OrderDetailDTO> orders) {
        return modelMapper.map(orders, new TypeToken<List<OrderDetail>>() {}.getType());
    }
}