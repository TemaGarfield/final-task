package by.kotik.please_work_jwt.service.impl;

import by.kotik.please_work_jwt.model.Collection;
import by.kotik.please_work_jwt.model.Topic;
import by.kotik.please_work_jwt.pojo.CollectionRequest;
import by.kotik.please_work_jwt.pojo.CollectionResponse;
import by.kotik.please_work_jwt.repository.CollectionRepository;
import by.kotik.please_work_jwt.repository.ItemRepository;
import by.kotik.please_work_jwt.repository.TopicRepository;
import by.kotik.please_work_jwt.repository.UserRepository;
import by.kotik.please_work_jwt.service.CollectionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;
    private final TopicRepository topicRepository;
    private final ItemRepository itemRepository;

    public CollectionServiceImpl(ItemRepository itemRepository, TopicRepository topicRepository, CollectionRepository collectionRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.topicRepository = topicRepository;
        this.collectionRepository = collectionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CollectionResponse> getAllCollections() {
        List<CollectionResponse> collections = new ArrayList<>();

        collectionRepository.findAll().forEach(collection -> {
            collections.add(new CollectionResponse(
                    collection.getId(),
                    collection.getName(),
                    collection.getTopic(),
                    collection.getDescription(),
                    collection.getUser(),
                    collection.getItems()
            ));
        });

        return collections;
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public CollectionResponse getCollectionById(Long id) {
        Collection collection = collectionRepository.getById(id);

        return new CollectionResponse(
                collection.getId(),
                collection.getName(),
                collection.getTopic(),
                collection.getDescription(),
                collection.getUser(),
                collection.getItems()
        );
    }

    @Override
    public List<CollectionResponse> getUserCollections(Long id) {
        List<CollectionResponse> collections = new ArrayList<>();

        collectionRepository.findAllByUser(userRepository.getById(id)).forEach(collection -> {
            collections.add(new CollectionResponse(
                    collection.getId(),
                    collection.getName(),
                    collection.getTopic(),
                    collection.getDescription(),
                    collection.getItems()
            ));
        });

        return collections;
    }

    @Override
    public Boolean deleteCollection(Long id) {
        collectionRepository.deleteById(id);

        return true;
    }

    @Override
    public Boolean saveCollection(CollectionRequest collectionRequest) {
        Collection collection = new Collection(collectionRequest.getName(), collectionRequest.getDescription(), collectionRequest.getTopic(), userRepository.findById(collectionRequest.getUserId()).get());
        collectionRepository.save(collection);

        return true;
    }

    @Override
    public Boolean editCollection(Long id, CollectionRequest collectionRequest) {
        Collection collection = new Collection(
                id,
                collectionRequest.getName(),
                collectionRequest.getDescription(),
                collectionRequest.getTopic(),
                userRepository.getById(collectionRequest.getUserId()),
                itemRepository.findAllByCollectionId(id)
        );

        collectionRepository.save(collection);

        return true;
    }

    private final static Integer MAX_ITEMS = 3;
    @Override
    public List<CollectionResponse> getMaxItemsCollections() {
        List<CollectionResponse> collectionResponses = new ArrayList<>();
        generateCollections().subList(0, MAX_ITEMS).forEach(collection -> {
            collectionResponses.add(new CollectionResponse(collection.getId(), collection.getName()));
        });
        return collectionResponses;
    }

    private Map<Collection, Long> generateCollectionItems() {
        Map<Collection, Long> collectionItems = new HashMap<>();
        collectionRepository.findAll().forEach(collection -> {
            collectionItems.put(collection, itemRepository.countItemsByCollection(collection));
        });
        return collectionItems;
    }

    private List<Collection> generateCollections() {
        List<Collection> collections = new ArrayList<>();
        generateCollectionItems().entrySet().stream()
                .sorted(Map.Entry.<Collection, Long>comparingByValue().reversed())
                .forEach(collectionLongEntry -> {
                    collections.add(collectionLongEntry.getKey());
                });

        return collections;
    }
}
