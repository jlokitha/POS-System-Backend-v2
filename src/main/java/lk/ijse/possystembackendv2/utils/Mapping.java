package lk.ijse.possystembackendv2.utils;

import lk.ijse.possystembackendv2.dto.impl.CustomerDTO;
import lk.ijse.possystembackendv2.entity.impl.Customer;
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
}
