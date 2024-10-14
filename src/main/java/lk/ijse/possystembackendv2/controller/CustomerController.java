package lk.ijse.possystembackendv2.controller;

import jakarta.servlet.http.HttpServlet;
import lk.ijse.possystembackendv2.dto.impl.CustomerDTO;
import lk.ijse.possystembackendv2.exception.CustomerNotFountException;
import lk.ijse.possystembackendv2.exception.DataPersistFailedException;
import lk.ijse.possystembackendv2.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController extends HttpServlet {
    @Autowired
    private CustomerService customerService;
@GetMapping("/test")
    public String test() {
        return "test";
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO dto) {
        try {
            customerService.saveCustomer(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String userId) {
        try {
            customerService.deleteCustomer(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFountException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getSelectedUser(@PathVariable("id") String userId) {
        try {
            CustomerDTO customerById = customerService.findCustomerById(userId);
            return new ResponseEntity<>(customerById, HttpStatus.OK);
        } catch (CustomerNotFountException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getAllUsers() {
        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@PathVariable("id") String userId, @RequestBody CustomerDTO dto) {
        try {
            dto.setId(userId);
            customerService.updateCustomer(dto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFountException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
