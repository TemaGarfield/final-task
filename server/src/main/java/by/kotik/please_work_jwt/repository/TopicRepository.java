package by.kotik.please_work_jwt.repository;

import by.kotik.please_work_jwt.model.ETopic;
import by.kotik.please_work_jwt.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findByTopic(ETopic topic);
    List<Topic> findAll();
}
