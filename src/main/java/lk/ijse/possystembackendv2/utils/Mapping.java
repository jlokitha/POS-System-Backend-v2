package lk.ijse.possystembackendv2.utils;

import lk.ijse.possystembackendv2.dto.impl.CustomerDTO;
import lk.ijse.possystembackendv2.dto.impl.ItemDTO;
import lk.ijse.possystembackendv2.entity.impl.Customer;
import lk.ijse.possystembackendv2.entity.impl.Item;
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
}
