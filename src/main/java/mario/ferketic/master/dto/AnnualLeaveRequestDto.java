package mario.ferketic.master.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnualLeaveRequestDto {
    private String employeeUsername;
    private LocalDate start;
    private LocalDate end;
}
