package mario.ferketic.master.controller.annual_leave;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.dto.AnnualLeaveRequestDto;
import mario.ferketic.master.entity.users.AnnualLeave;
import mario.ferketic.master.service.AnnualLeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("annual_leave_developer")
@RequiredArgsConstructor
public class AnnualLeaveDeveloperController {
    private final AnnualLeaveService annualLeaveService;

    @PostMapping("/request")
    public ResponseEntity<AnnualLeave> requestAnnualLeave(@RequestBody AnnualLeaveRequestDto request) {
        AnnualLeave requestedAnnualLeave = annualLeaveService.requestAnnualLeave(request.getEmployeeUsername(), request.getStart(), request.getEnd());
        return ResponseEntity.ok(requestedAnnualLeave);
    }

    @GetMapping("/{employeeUsername}")
    public ResponseEntity<Integer> getDaysLeft(@PathVariable String employeeUsername) {
        return ResponseEntity.ok(annualLeaveService.getDaysLeft(employeeUsername));
    }

    //TODO get all annual leaves
}
