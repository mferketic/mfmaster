package mario.ferketic.master.controller.annual_leave;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.entity.users.AnnualLeave;
import mario.ferketic.master.service.AnnualLeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("annual_leave_manager")
@RequiredArgsConstructor
public class AnnualLeaveManagerController {

    private final AnnualLeaveService annualLeaveService;

    @PostMapping("{managerUsername}/{employeeUsername}")
    public ResponseEntity<AnnualLeave> approveAnnualLeave(@PathVariable String managerUsername, @PathVariable String employeeUsername) {
        return ResponseEntity.accepted().body(annualLeaveService.approveAnnualLeave(managerUsername, employeeUsername));
    }

    @PatchMapping("{managerUsername}/{employeeUsername}")
    public ResponseEntity<AnnualLeave> rejectAnnualLeave(@PathVariable String managerUsername, @PathVariable String employeeUsername) {
        return ResponseEntity.accepted().body(annualLeaveService.rejectAnnualLeave(managerUsername, employeeUsername));
    }
}
