package by.kotik.please_work_jwt.pojo;

import lombok.Data;

@Data
public class CommentRequest {
    private String message;
    private Long userFromId;

    public CommentRequest() {
    }

    public CommentRequest(String message, Long userFromId) {
        this.message = message;
        this.userFromId = userFromId;
    }
}
