package by.kotik.please_work_jwt.repository;

import by.kotik.please_work_jwt.model.Comment;
import by.kotik.please_work_jwt.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByToItem(Item item);
}
