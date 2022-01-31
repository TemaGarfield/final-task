package by.kotik.please_work_jwt.pojo;

import by.kotik.please_work_jwt.model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class ItemRequest {
    private Long id;
    private String name;
    private Long collectionId;
    private List<Comment> comments;

    public ItemRequest() {
    }

    public ItemRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ItemRequest(String name, Long collectionId) {
        this.name = name;
        this.collectionId = collectionId;
    }

    public ItemRequest(String name, Long collectionId, List<Comment> comments) {
        this.name = name;
        this.collectionId = collectionId;
        this.comments = comments;
    }
}
