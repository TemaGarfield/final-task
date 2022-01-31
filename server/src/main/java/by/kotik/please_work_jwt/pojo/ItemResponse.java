package by.kotik.please_work_jwt.pojo;

import by.kotik.please_work_jwt.model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class ItemResponse {
    private Long id;
    private String name;
    private List<Comment> comments;

    public ItemResponse() {
    }

    public ItemResponse(Long id, String name, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.comments = comments;
    }

    public ItemResponse(String name, List<Comment> comments) {
        this.name = name;
        this.comments = comments;
    }
}
