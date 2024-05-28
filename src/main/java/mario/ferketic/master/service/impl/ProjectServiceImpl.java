package mario.ferketic.master.service.impl;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.entity.auth.AuthoritiesEntity;
import mario.ferketic.master.entity.project.Project;
import mario.ferketic.master.entity.project.ProjectWork;
import mario.ferketic.master.entity.users.User;
import mario.ferketic.master.exceptions.ConflictException;
import mario.ferketic.master.repository.project.ProjectRepository;
import mario.ferketic.master.repository.project.ProjectWorkRepository;
import mario.ferketic.master.service.ManagementService;
import mario.ferketic.master.service.ProjectService;
import mario.ferketic.master.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectWorkRepository projectWorkRepository;
    private final UserService userService;
    private final ManagementService managementService;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project createProject(Project project) {
        if (project.getProject_id() != null && projectRepository.findById(project.getProject_id()).isPresent()) {
            throw new ConflictException(String.format("Project with project_id %s already exists", project.getProject_id()));
        }
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        var existingProject = projectRepository.findById(project.getProject_id());
        if (existingProject.isEmpty()) {
            throw new ConflictException(String.format("Project with project_id %s does not exist", project.getProject_id()));
        }
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> getByProjectId(Long project_id) {
        return projectRepository.findById(project_id);
    }

    @Override
    public void deleteProjectById(Long project_id) {
        projectRepository.deleteById(project_id);
    }

    @Override
    public ProjectWork addDeveloperToProject(String managerUsername, String employeeUsername, Long project_id) {
        managementService.checkIfManagerManagesEmployee(managerUsername, employeeUsername);
        var project = projectRepository.findById(project_id);
        if (project.isEmpty()) {
            throw new ConflictException(String.format("Project with project_id %s does not exist", project_id));
        }
        var projectWork = projectWorkRepository.findProjectWorkByProject(project.get());
        if (projectWork.isEmpty()) {
            projectWork = Optional.of(new ProjectWork());
            //TODO check if user is already in some project, if yes delete that project from him
        }
        var employee = userService.getByUsername(employeeUsername);
        projectWork.get().addDeveloper(employee);
        projectWork.get().setProject(project.get());
        return projectWorkRepository.save(projectWork.get());
    }

}
