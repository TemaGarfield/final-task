package by.kotik.please_work_jwt.repository;

import by.kotik.please_work_jwt.model.Collection;
import by.kotik.please_work_jwt.model.Item;
import by.kotik.please_work_jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    List<Collection> findAllByUser(User user);

}
