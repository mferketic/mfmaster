package mario.ferketic.master.dto.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import mario.ferketic.master.dto.Dto;
import mario.ferketic.master.entity.auth.AuthoritiesEntity;
import mario.ferketic.master.entity.users.details.City;
import mario.ferketic.master.enums.Role;
import mario.ferketic.master.enums.Title;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto implements Dto {
    @Length(min = 3, message = "The field of username must be at least 3 characters long")
    private String username;
    @Length(min = 8, message = "The field of password must be at least 8 characters long")
    private String password;
    @Length(min = 3, message = "The field of firstname must be at least 3 characters long")
    @NotEmpty
    private String firstname;
    @Length(min = 3, message = "The field of lastname must be at least 3 characters long")
    @NotEmpty
    private String lastname;
    @Email
    private String email;
    @Valid
    private City city;
    @Valid
    private Title title;
    private List<Role> roleList;

    public UserDto(String username, String password, String firstname, String lastname, String email, City postalcode, Title title, List<AuthoritiesEntity> authoritiesEntityList) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.city = postalcode;
        this.title = title;
        for (AuthoritiesEntity authoritiesEntity : authoritiesEntityList) {
            if (this.roleList == null) {
                this.roleList = new ArrayList<>();
            }
            this.roleList.add(Role.valueOf(authoritiesEntity.getAuthority()));
        }
    }
}