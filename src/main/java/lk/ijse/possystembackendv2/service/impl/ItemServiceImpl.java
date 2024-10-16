package lk.ijse.possystembackendv2.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendv2.dto.impl.ItemDTO;
import lk.ijse.possystembackendv2.entity.impl.Item;
import lk.ijse.possystembackendv2.exception.DataPersistFailedException;
import lk.ijse.possystembackendv2.exception.ItemNotFoundException;
import lk.ijse.possystembackendv2.repository.ItemRepository;
import lk.ijse.possystembackendv2.service.ItemService;
import lk.ijse.possystembackendv2.utils.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final Mapping mapping;
    @Override
    public void saveItem(ItemDTO dto) {
        Item save = itemRepository.save(mapping.toEntity(dto));
        if (save == null) {
            throw new DataPersistFailedException("Item save failed");
        }
    }
    @Override
    public void updateItem(ItemDTO dto) {
        Optional<Item> item = itemRepository.findById(dto.getId());
        if (item.isEmpty()) throw new ItemNotFoundException("Item not found");
        else {
            item.get().setDescription(dto.getDescription());
            item.get().setQuantity(dto.getQuantity());
            item.get().setPrice(dto.getPrice());
        }
    }
    @Override
    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) throw new ItemNotFoundException("Item not found");
        else {
            itemRepository.deleteById(id);
        }
    }
    @Override
    public ItemDTO findItemById(Long id) {
        Optional<Item> byId = itemRepository.findById(id);
        if (byId.isEmpty()) throw new ItemNotFoundException("Item not found");
        else return mapping.toDto(byId.get());
    }
    @Override
    public List<ItemDTO> findAllItems() {
        return mapping.toItemDtoList(itemRepository.findAll());
    }
}
