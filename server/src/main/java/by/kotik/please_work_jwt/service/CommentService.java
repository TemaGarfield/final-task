package by.kotik.please_work_jwt.service;

import by.kotik.please_work_jwt.pojo.CommentRequest;

public interface CommentService {
    Boolean saveComment(Long id, CommentRequest commentRequest);
}
