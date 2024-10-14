package lk.ijse.possystembackendv2.service;

import lk.ijse.possystembackendv2.dto.impl.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void saveCustomer(CustomerDTO dto);
    void updateCustomer(CustomerDTO dto);
    void deleteCustomer(String id);
    CustomerDTO findCustomerById(String id);
    List<CustomerDTO> findAllCustomers();
}
