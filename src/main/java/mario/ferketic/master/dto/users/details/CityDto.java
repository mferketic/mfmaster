package mario.ferketic.master.dto.users.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mario.ferketic.master.dto.Dto;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto implements Dto {
    @NotNull(message = "zipCode is required!")
    @Range(min = 10000, max = 99999)
    private int postalcode;
    @NotEmpty(message = "name is required!")
    @Size(min = 2, message = "name must have min 2 characters")
    private String name;
}
