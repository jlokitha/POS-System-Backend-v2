package lk.ijse.possystembackendv2.controller;

import lk.ijse.possystembackendv2.dto.impl.CustomerDTO;
import lk.ijse.possystembackendv2.exception.CustomerNotFountException;
import lk.ijse.possystembackendv2.exception.DataPersistFailedException;
import lk.ijse.possystembackendv2.service.CustomerService;
import lk.ijse.possystembackendv2.utils.Base64Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;
    /**
     * Creates a new customer with the provided details.
     *
     * @param name        the name of the customer.
     * @param email       the email address of the customer.
     * @param password    the password for the customer's account.
     * @param address     the address of the customer.
     * @param salary      the salary of the customer.
     * @param profilePic  the profile picture of the customer as a MultipartFile.
     * @return ResponseEntity indicating the result of the operation, with a status of
     *         CREATED if successful, BAD_REQUEST if data persistence fails,
     *         or INTERNAL_SERVER_ERROR for unexpected errors.
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createCustomer(
            @RequestPart("name") String name,
            @RequestPart ("email") String email,
            @RequestPart ("password") String password,
            @RequestPart ("address") String address,
            @RequestPart ("salary") String salary,
            @RequestPart ("profilePic") MultipartFile profilePic) {
        try {
            CustomerDTO dto = CustomerDTO.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .address(address)
                    .salary(Double.parseDouble(salary))
                    .profilePic(Base64Handler.toBase64ProfilePic(profilePic))
                    .build();
            customerService.saveCustomer(dto);
            log.info("Customer created successfully: {}", dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            log.error("Data persist failed for customer with email: {} | Error: {}", email, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("An unexpected error occurred while creating customer with email: {} | Error: {}", email, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Updates the details of an existing customer identified by the provided ID.
     *
     * @param customerId  the ID of the customer to be updated.
     * @param name        the new name of the customer.
     * @param email       the new email address of the customer.
     * @param password    the new password for the customer's account.
     * @param address     the new address of the customer.
     * @param salary      the new salary of the customer.
     * @param profilePic  the new profile picture of the customer as a MultipartFile.
     * @return ResponseEntity indicating the result of the operation, with a status of
     *         NO_CONTENT if successful, NOT_FOUND if the customer is not found,
     *         or INTERNAL_SERVER_ERROR for unexpected errors.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCustomer(
            @PathVariable("id") Long customerId,
            @RequestPart("name") String name,
            @RequestPart ("email") String email,
            @RequestPart ("password") String password,
            @RequestPart ("address") String address,
            @RequestPart ("salary") String salary,
            @RequestPart ("profilePic") MultipartFile profilePic) {
        try {
            CustomerDTO dto = CustomerDTO.builder()
                    .id(customerId)
                    .name(name)
                    .email(email)
                    .password(password)
                    .address(address)
                    .salary(Double.parseDouble(salary))
                    .profilePic(Base64Handler.toBase64ProfilePic(profilePic))
                    .build();
            dto.setId(customerId);
            customerService.updateCustomer(dto);
            log.info("Customer updated successfully: {}", dto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFountException e) {
            log.warn("Customer not found for update: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("An unexpected error occurred while updating customer with email: {} | Error: {}", email, e.getMessage());
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