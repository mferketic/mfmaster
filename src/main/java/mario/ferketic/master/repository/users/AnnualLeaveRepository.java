package mario.ferketic.master.repository.users;

import mario.ferketic.master.entity.users.AnnualLeave;
import mario.ferketic.master.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnualLeaveRepository extends JpaRepository<AnnualLeave, String> {
    List<AnnualLeave> findAnnualLeavesByEmployee(User user);
}
