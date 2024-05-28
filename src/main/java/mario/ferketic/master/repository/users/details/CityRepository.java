package mario.ferketic.master.repository.users.details;

import mario.ferketic.master.entity.users.details.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {


}
