package mario.ferketic.master.service;

import mario.ferketic.master.entity.project.Project;
import mario.ferketic.master.entity.project.ProjectWork;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAllProjects();

    Project createProject(Project project);

    Project updateProject(Project project);

    Optional<Project> getByProjectId(Long project_id);

    void deleteProjectById(Long project_id);

    ProjectWork addDeveloperToProject(String managerUsername, String employeeUsername, Long project_id);
}
