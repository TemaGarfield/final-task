package by.kotik.please_work_jwt.pojo;

import by.kotik.please_work_jwt.model.User;
import lombok.Data;

@Data
public class CommentResponse {
    private String message;
    private User user;

    public CommentResponse() {
    }

    public CommentResponse(String message) {
        this.message = message;
    }

    public CommentResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }
}
