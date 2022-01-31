package by.kotik.please_work_jwt.service;

import by.kotik.please_work_jwt.model.Topic;
import by.kotik.please_work_jwt.pojo.CollectionRequest;
import by.kotik.please_work_jwt.pojo.CollectionResponse;

import java.util.List;

public interface CollectionService {
    List<CollectionResponse> getAllCollections();
    List<Topic> getAllTopics();
    CollectionResponse getCollectionById(Long id);
    List<CollectionResponse> getUserCollections(Long id);
    Boolean deleteCollection(Long id);
    Boolean saveCollection(CollectionRequest collectionRequest);
    Boolean editCollection(Long id, CollectionRequest collectionRequest);
    List<CollectionResponse> getMaxItemsCollections();
}
