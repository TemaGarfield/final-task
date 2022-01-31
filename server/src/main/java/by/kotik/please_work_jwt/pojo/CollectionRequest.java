package by.kotik.please_work_jwt.pojo;

import by.kotik.please_work_jwt.model.Topic;
import lombok.Data;

@Data
public class CollectionRequest {
    private Long userId;
    private String name;
    private Topic topic;
    private String description;

    public CollectionRequest() {}

    public CollectionRequest(Long userId, String name, Topic topic, String description) {
        this.userId = userId;
        this.name = name;
        this.topic = topic;
        this.description = description;
    }
}
