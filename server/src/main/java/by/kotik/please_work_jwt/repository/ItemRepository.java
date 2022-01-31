package by.kotik.please_work_jwt.repository;

import by.kotik.please_work_jwt.model.Collection;
import by.kotik.please_work_jwt.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByCollectionId(Long collectionId);
    Long countItemsByCollection(Collection collection);
    List<Item> findTop3ByOrderByIdDesc();
}
