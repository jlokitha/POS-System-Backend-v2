package lk.ijse.possystembackendv2.controller;

import lk.ijse.possystembackendv2.dto.impl.ItemDTO;
import lk.ijse.possystembackendv2.exception.DataPersistFailedException;
import lk.ijse.possystembackendv2.exception.ItemNotFoundException;
import lk.ijse.possystembackendv2.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;
    /**
     * Creates a new item.
     *
     * @param dto the item data transfer object containing item details
     * @return HTTP status indicating the result of the operation
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDTO dto) {
        try {
            itemService.saveItem(dto);
            log.info("Item created successfully: {}", dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            log.error("Data persist failed for item: {} | Error: {}", dto, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("An unexpected error occurred while creating item: {} | Error: {}", dto, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Updates an existing item by its ID.
     *
     * @param itemId the ID of the item to update
     * @param dto    the item data transfer object containing updated item details
     * @return HTTP status indicating the result of the operation
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@PathVariable("id") Long itemId, @RequestBody ItemDTO dto) {
        try {
            dto.setId(itemId); // Set the ID for the DTO before updating
            itemService.updateItem(dto);
            log.info("Item updated successfully: {}", dto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            log.warn("Item not found for update: {}", itemId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("An unexpected error occurred while updating item: {} | Error: {}", dto, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Deletes an item by its ID.
     *
     * @param itemId the ID of the item to delete
     * @return HTTP status indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Long itemId) {
        try {
            itemService.deleteItem(itemId);
            log.info("Item deleted successfully: {}", itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            log.warn("Item not found for deletion: {}", itemId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("An unexpected error occurred while deleting item: {} | Error: {}", itemId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Retrieves an item by its ID.
     *
     * @param itemId the ID of the item to retrieve
     * @return the item data transfer object, or HTTP status if not found
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDTO> getSelectedItem(@PathVariable("id") Long itemId) {
        try {
            ItemDTO itemById = itemService.findItemById(itemId);
            log.info("Item retrieved successfully: {}", itemById);
            return new ResponseEntity<>(itemById, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            log.warn("Item not found: {}", itemId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("An unexpected error occurred while retrieving item: {} | Error: {}", itemId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Retrieves all items.
     *
     * @return a list of all items
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        try {
            List<ItemDTO> items = itemService.findAllItems();
            log.info("Retrieved {} items", items.size());
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            log.error("An unexpected error occurred while retrieving all items. | Error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}