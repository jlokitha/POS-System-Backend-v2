package lk.ijse.possystembackendv2.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendv2.dto.impl.CustomerDTO;
import lk.ijse.possystembackendv2.entity.impl.Customer;
import lk.ijse.possystembackendv2.exception.CustomerNotFountException;
import lk.ijse.possystembackendv2.exception.DataPersistFailedException;
import lk.ijse.possystembackendv2.repository.CustomerRepository;
import lk.ijse.possystembackendv2.service.CustomerService;
import lk.ijse.possystembackendv2.utils.IdGenerator;
import lk.ijse.possystembackendv2.utils.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final Mapping mapping;

    @Override
    public void saveCustomer(CustomerDTO dto) {
        dto.setId(IdGenerator.generateId());
        Customer save = customerRepository.save(mapping.toEntity(dto));
        if (save == null) {
            throw new DataPersistFailedException("Customer save failed");
        }
    }

    @Override
    public void updateCustomer(CustomerDTO dto) {
        Optional<Customer> customer = customerRepository.findById(dto.getId());
        if (customer.isEmpty()) throw new CustomerNotFountException("Customer not found");
        else {
            customer.get().setName(dto.getName());
            customer.get().setEmail(dto.getEmail());
            customer.get().setAddress(dto.getAddress());
            customer.get().setSalary(dto.getSalary());
        }
    }

    @Override
    public CustomerDTO findCustomerById(String id) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isEmpty()) throw new CustomerNotFountException("Customer not found");
        else return mapping.toDto(byId.get());
    }

    @Override
    public void deleteCustomer(String id) {
        if (!customerRepository.existsById(id)) throw new CustomerNotFountException("Customer not found");
        else {
            customerRepository.deleteById(id);
        }
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return mapping.toCustomerDtoList(customerRepository.findAll());
    }
}
