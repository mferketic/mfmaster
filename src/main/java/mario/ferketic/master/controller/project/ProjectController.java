package mario.ferketic.master.controller.project;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.dto.ProjectDto;
import mario.ferketic.master.exceptions.BadRequestException;
import mario.ferketic.master.mapper.ProjectMapper;
import mario.ferketic.master.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects_owner")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll() {
        return ResponseEntity.ok().body(projectService.getAllProjects().stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto) {
        var savedProject = projectService.createProject(projectMapper.toEntity(projectDto));
        return ResponseEntity.accepted().body(projectMapper.toDto(savedProject));
    }


    @GetMapping("/{project_id}")
    public ResponseEntity<ProjectDto> getProjectByProjectId(@PathVariable Long project_id) {
        return projectService.getByProjectId(project_id).map(
                foundProject -> ResponseEntity.ok().body(projectMapper.toDto(foundProject))
        ).orElseThrow(() -> new BadRequestException(String.format("Project with project_id %s does not exist", project_id)));
    }

    @PatchMapping()
    public ResponseEntity<ProjectDto> updateProject(@Valid @RequestBody ProjectDto projectDto) {
        var savedProject = projectService.updateProject(projectMapper.toEntity(projectDto));
        return ResponseEntity.accepted().body(projectMapper.toDto(savedProject));
    }

    @DeleteMapping("/{project_id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long project_id) {
        projectService.deleteProjectById(project_id);
        return ResponseEntity.noContent().build();
    }
}
