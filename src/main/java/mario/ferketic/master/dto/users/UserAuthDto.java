package mario.ferketic.master.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mario.ferketic.master.dto.Dto;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDto implements Dto {
    @Length(min = 3, message = "The field of username must be at least 3 characters long")
    private String username;
    @Length(min = 8, message = "The field of password must be at least 8 characters long")
    private String password;
}