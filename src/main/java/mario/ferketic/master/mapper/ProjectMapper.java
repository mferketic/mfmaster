package mario.ferketic.master.mapper;

import mario.ferketic.master.dto.ProjectDto;
import mario.ferketic.master.entity.project.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements GenericMapper<Project, ProjectDto> {

    @Override
    public Project toEntity(ProjectDto dto) {
        Project project = new Project();
        project.setProject_id(dto.getProject_id());
        project.setName(dto.getName());
        project.setBudget(dto.getBudget());
        project.setFinish_date(dto.getFinishDay());
        return project;
    }

    @Override
    public ProjectDto toDto(Project entity) {
        ProjectDto dto = new ProjectDto();
        dto.setProject_id(entity.getProject_id());
        dto.setName(entity.getName());
        dto.setBudget(entity.getBudget());
        dto.setFinishDay(entity.getFinish_date());
        return dto;
    }
}
