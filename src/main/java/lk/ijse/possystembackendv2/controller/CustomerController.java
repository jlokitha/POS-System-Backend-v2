package lk.ijse.possystembackendv2.controller;

import lk.ijse.possystembackendv2.dto.impl.CustomerDTO;
import lk.ijse.possystembackendv2.exception.CustomerNotFountException;
import lk.ijse.possystembackendv2.exception.DataPersistFailedException;
import lk.ijse.possystembackendv2.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;
    /**
     * Creates a new customer.
     *
     * @param dto the customer data transfer object containing customer details.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDTO dto) {
        try {
            customerService.saveCustomer(dto);
            log.info("Customer created successfully: {}", dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            log.error("Data persist failed for customer: {} | Error: {}", dto, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("An unexpected error occurred while creating customer: {} | Error: {}", dto, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Updates an existing customer identified by the provided ID.
     *
     * @param customerId the ID of the customer to update.
     * @param dto the customer data transfer object containing updated customer details.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerDTO dto) {
        try {
            dto.setId(customerId);
            customerService.updateCustomer(dto);
            log.info("Customer updated successfully: {}", dto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFountException e) {
            log.warn("Customer not found for update: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("An unexpected error occurred while updating customer: {} | Error: {}", dto, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Deletes a customer identified by the provided ID.
     *
     * @param customerId the ID of the customer to delete.
     * @return ResponseEntity indicating the result of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            log.info("Customer deleted successfully: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFountException e) {
            log.warn("Customer not found for deletion: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("An unexpected error occurred while deleting customer: {} | Error: {}", customerId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Retrieves a customer identified by the provided ID.
     *
     * @param customerId the ID of the customer to retrieve.
     * @return ResponseEntity containing the customer data transfer object and HTTP status.
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Long customerId) {
        try {
            CustomerDTO customerById = customerService.findCustomerById(customerId);
            log.info("Customer retrieved successfully: {}", customerById);
            return new ResponseEntity<>(customerById, HttpStatus.OK);
        } catch (CustomerNotFountException e) {
            log.warn("Customer not found: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("An unexpected error occurred while retrieving customer: {} | Error: {}", customerId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Retrieves all customers.
     *
     * @return ResponseEntity containing a list of customer data transfer objects and HTTP status.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.findAllCustomers();
        log.info("Retrieved {} customers", customers.size());
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}