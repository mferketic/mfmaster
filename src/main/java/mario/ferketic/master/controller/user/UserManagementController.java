package mario.ferketic.master.controller.user;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.entity.Management;
import mario.ferketic.master.service.ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("management")
@RequiredArgsConstructor
public class UserManagementController {

    private final ManagementService managementService;

    @PostMapping("{managerUsername}/{employeeUsername}")
    public ResponseEntity<Management> createManagerForEmployee(@PathVariable String managerUsername, @PathVariable String employeeUsername) {
        var savedManagementForEmployee = managementService.createManagerForEmployee(managerUsername, employeeUsername);
        return ResponseEntity.accepted().body(savedManagementForEmployee);
    }

    @PatchMapping("/{managerUsername}/{employeeUsername}")
    public ResponseEntity<Management> updateManagerForEmployee(@PathVariable String managerUsername, @PathVariable String employeeUsername) {
        var savedManagementForEmployee = managementService.updateManagerForEmployee(managerUsername, employeeUsername);
        return ResponseEntity.accepted().body(savedManagementForEmployee);
    }


}
