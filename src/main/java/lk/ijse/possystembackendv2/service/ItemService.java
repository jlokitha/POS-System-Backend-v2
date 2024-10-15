package lk.ijse.possystembackendv2.service;

import lk.ijse.possystembackendv2.dto.impl.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    void saveItem(ItemDTO dto);
    void updateItem(ItemDTO dto);
    void deleteItem(Long id);
    ItemDTO findItemById(Long id);
    List<ItemDTO> findAllItems();
}
