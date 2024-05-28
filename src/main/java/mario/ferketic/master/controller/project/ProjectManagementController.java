package mario.ferketic.master.controller.project;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.entity.project.ProjectWork;
import mario.ferketic.master.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project_management")
@RequiredArgsConstructor
public class ProjectManagementController {

    private final ProjectService projectService;

    @PatchMapping("/{managerUsername}/{employeeUsername}/{project_id}")
    public ResponseEntity<ProjectWork> addDeveloperToProject(@PathVariable String managerUsername,
                                                             @PathVariable String employeeUsername, @PathVariable Long project_id) {
        var savedProjectWork = projectService.addDeveloperToProject(managerUsername, employeeUsername, project_id);
        return ResponseEntity.accepted().body(savedProjectWork);
    }


}
