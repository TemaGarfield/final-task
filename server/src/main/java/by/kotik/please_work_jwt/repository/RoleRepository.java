package by.kotik.please_work_jwt.repository;

import by.kotik.please_work_jwt.model.ERole;
import by.kotik.please_work_jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
