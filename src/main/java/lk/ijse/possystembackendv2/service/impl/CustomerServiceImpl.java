package lk.ijse.possystembackendv2.service.impl;

import lk.ijse.possystembackendv2.customObj.CustomerErrorResponse;
import lk.ijse.possystembackendv2.customObj.CustomerResponse;
import lk.ijse.possystembackendv2.dto.impl.CustomerDTO;
import lk.ijse.possystembackendv2.entity.impl.Customer;
import lk.ijse.possystembackendv2.exception.CustomerNotFountException;
import lk.ijse.possystembackendv2.exception.DataPersistFailedException;
import lk.ijse.possystembackendv2.repository.CustomerRepository;
import lk.ijse.possystembackendv2.service.CustomerService;
import lk.ijse.possystembackendv2.utils.IdGenerator;
import lk.ijse.possystembackendv2.utils.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Mapping mapping;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, Mapping mapping) {
        this.customerRepository = customerRepository;
        this.mapping = mapping;
    }

    @Override
    public void saveCustomer(CustomerDTO dto) {
        dto.setId(IdGenerator.generateId());
        Customer save = customerRepository.save(mapping.toEntity(dto));
        if (save == null && save.getId() == null) {
            throw new DataPersistFailedException("Customer save failed");
        }
    }

    @Override
    public void updateCustomer(CustomerDTO dto) {
        Optional<Customer> customer = customerRepository.findById(dto.getId());
        if (!customer.isPresent()) throw new DataPersistFailedException("Customer not found");
        else {
            customer.get().setName(dto.getName());
            customer.get().setAddress(dto.getAddress());
            customer.get().setSalary(dto.getSalary());
        }
    }

    @Override
    public CustomerResponse findCustomerById(String id) {
        if (customerRepository.existsById(id)) {
            Optional<Customer> customer = customerRepository.findById(id);
            return customer.isEmpty() ? null : mapping.toDto(customer.orElse(null));
        } else {
            return new CustomerErrorResponse(0, "Customer not found");
        }
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
