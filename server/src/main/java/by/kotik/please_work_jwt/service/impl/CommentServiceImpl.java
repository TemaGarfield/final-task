package by.kotik.please_work_jwt.service.impl;

import by.kotik.please_work_jwt.model.Comment;
import by.kotik.please_work_jwt.pojo.CommentRequest;
import by.kotik.please_work_jwt.repository.CommentRepository;
import by.kotik.please_work_jwt.repository.ItemRepository;
import by.kotik.please_work_jwt.repository.UserRepository;
import by.kotik.please_work_jwt.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(UserRepository userRepository, ItemRepository itemRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Boolean saveComment(Long id, CommentRequest commentRequest) {
        Comment comment = new Comment();

        comment.setMessage(commentRequest.getMessage());
        comment.setFromUser(userRepository.findById(commentRequest.getUserFromId()).get());
        comment.setToItem(itemRepository.getById(id));

        commentRepository.save(comment);
        return null;
    }
}
