package by.kotik.please_work_jwt.service.impl;

import by.kotik.please_work_jwt.model.Item;
import by.kotik.please_work_jwt.pojo.ItemRequest;
import by.kotik.please_work_jwt.pojo.ItemResponse;
import by.kotik.please_work_jwt.repository.CollectionRepository;
import by.kotik.please_work_jwt.repository.CommentRepository;
import by.kotik.please_work_jwt.repository.ItemRepository;
import by.kotik.please_work_jwt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;
    private final CollectionRepository collectionRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CommentRepository commentRepository, CollectionRepository collectionRepository) {
        this.itemRepository = itemRepository;
        this.commentRepository = commentRepository;
        this.collectionRepository = collectionRepository;
    }

    @Override
    public ItemResponse getItemById(Long id) {
        Item item = itemRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Item not found"));
        return new ItemResponse(
                item.getId(),
                item.getName(),
                commentRepository.findAllByToItem(item)
        );
    }

    @Override
    public Boolean deleteItem(Long id) {
        itemRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean saveItem(ItemRequest itemRequest) {
        itemRepository.save(
                new Item(
                        itemRequest.getName(),
                        collectionRepository.getById(itemRequest.getCollectionId()
                        ))
        );
        return true;
    }

    @Override
    public Boolean editItem(ItemRequest itemRequest) {
        itemRepository.save(
                new Item(
                        itemRequest.getId(),
                        itemRequest.getName(),
                        collectionRepository.getById(itemRequest.getCollectionId()),
                        commentRepository.findAllByToItem(itemRepository
                                .findById(itemRequest.getId())
                                .orElseThrow(() -> new RuntimeException("Item not found"))
                        ))
        );
        return true;
    }

    @Override
    public List<ItemResponse> getLastAddedItems() {
        List<ItemResponse> itemResponses = new ArrayList<>();

        itemRepository.findTop3ByOrderByIdDesc().forEach(item -> {
            itemResponses.add(new ItemResponse(item.getId(), item.getName(), item.getComments()));
        });

        return itemResponses;
    }
}
