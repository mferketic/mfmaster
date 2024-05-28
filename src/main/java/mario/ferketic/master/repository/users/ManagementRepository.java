package mario.ferketic.master.repository.users;

import mario.ferketic.master.entity.Management;
import mario.ferketic.master.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagementRepository extends JpaRepository<Management, String> {
    Optional<Management> findByManagerAndEmployee(User manager, User employee);

    Optional<Management> findManagementByEmployee(User manager);

}
