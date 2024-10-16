package lk.ijse.possystembackendv2.controller;

import lk.ijse.possystembackendv2.dto.impl.OrderDTO;
import lk.ijse.possystembackendv2.exception.DataPersistFailedException;
import lk.ijse.possystembackendv2.exception.OrderNotFoundException;
import lk.ijse.possystembackendv2.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    /**
     * Creates a new order.
     *
     * @param orderDTO the order data transfer object containing order details
     * @return HTTP status indicating the result of the operation
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDTO orderDTO) {
        try {
            orderService.saveOrder(orderDTO);
            log.info("Order created successfully: {}", orderDTO); // Log order creation
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            log.error("Data persist failed for order: {} | Error: {}", orderDTO, e.getMessage()); // Log specific error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("An unexpected error occurred while saving order: {} | Error: {}", orderDTO, e.getMessage()); // Log unexpected error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order to retrieve
     * @return the order data transfer object, or HTTP status if not found
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getSelectedOrder(@PathVariable("id") Long id) {
        try {
            OrderDTO orderDTO = orderService.getSelectedOrder(id);
            log.info("Order retrieved successfully: {}", orderDTO);
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            log.warn("Order not found with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("An unexpected error occurred while retrieving order ID: {} | Error: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Retrieves all orders.
     *
     * @return a list of all orders
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        try {
            List<OrderDTO> orders = orderService.getAllOrders();
            log.info("Retrieved {} orders", orders.size());
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            log.error("An unexpected error occurred while retrieving all orders. | Error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}