package by.kotik.please_work_jwt.pojo;

import by.kotik.please_work_jwt.model.Item;
import by.kotik.please_work_jwt.model.Topic;
import by.kotik.please_work_jwt.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CollectionResponse {
    private Long id;
    private String name;
    private Topic topic;
    private String description;
    private User user;
    private List<Item> items;

    public CollectionResponse() { }

    public CollectionResponse(Long id, String name, Topic topic, String description, User user) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.description = description;
        this.user = user;
    }

    public CollectionResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CollectionResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CollectionResponse(Long id, String name, Topic topic, String description) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.description = description;
    }

    public CollectionResponse(Long id, String name, Topic topic, String description, User user, List<Item> items) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.description = description;
        this.user = user;
        this.items = items;
    }

    public CollectionResponse(Long id, String name, Topic topic, String description, List<Item> items) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.description = description;
        this.items = items;
    }
}
