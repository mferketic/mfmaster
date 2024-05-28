package mario.ferketic.master.dto;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ProjectDto implements Dto {

    private Long project_id;

    @NotEmpty
    private String name;

    @NotNull
    private int budget;

    @Future
    private LocalDate finishDay;
}
