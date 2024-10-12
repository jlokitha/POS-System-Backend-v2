package lk.ijse.possystembackendv2.utils;

import lk.ijse.possystembackendv2.dto.CustomerDTO;
import lk.ijse.possystembackendv2.entity.Customer;
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
    public CustomerDTO toDto(Customer note) {
        return modelMapper.map(note, CustomerDTO.class);
    }
    public Customer toEntity(CustomerDTO noteDTO) {
        return modelMapper.map(noteDTO, Customer.class);
    }
    public List<CustomerDTO> toNoteDtoList(List<Customer> notes) {
        return modelMapper.map(notes, new TypeToken<List<CustomerDTO>>() {}.getType());
    }
}
