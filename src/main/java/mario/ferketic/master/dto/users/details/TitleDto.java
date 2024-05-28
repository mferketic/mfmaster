package mario.ferketic.master.dto.users.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mario.ferketic.master.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleDto implements Dto {
    @NotNull(message = "titleid is required!")
    private Long titleid;
    @NotEmpty(message = "titlename must not be empty!")
    private String titlename;
}
