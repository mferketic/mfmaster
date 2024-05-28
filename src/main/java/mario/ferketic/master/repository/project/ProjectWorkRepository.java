package mario.ferketic.master.repository.project;

import mario.ferketic.master.entity.project.Project;
import mario.ferketic.master.entity.project.ProjectWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectWorkRepository extends JpaRepository<ProjectWork, Long> {
    Optional<ProjectWork> findProjectWorkByProject(Project project);
}
