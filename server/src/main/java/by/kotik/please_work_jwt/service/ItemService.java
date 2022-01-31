package by.kotik.please_work_jwt.service;

import by.kotik.please_work_jwt.pojo.ItemRequest;
import by.kotik.please_work_jwt.pojo.ItemResponse;

import java.util.List;

public interface ItemService {
    ItemResponse getItemById(Long id);
    Boolean deleteItem(Long id);
    Boolean saveItem(ItemRequest itemRequest);
    Boolean editItem(ItemRequest itemRequest);
    List<ItemResponse> getLastAddedItems();
}
