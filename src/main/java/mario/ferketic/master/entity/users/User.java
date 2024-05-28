package mario.ferketic.master.entity.users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mario.ferketic.master.entity.MyEntity;
import mario.ferketic.master.entity.auth.AuthoritiesEntity;
import mario.ferketic.master.entity.users.details.City;
import mario.ferketic.master.enums.Role;
import mario.ferketic.master.enums.Title;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class User implements MyEntity {

    @Id
    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 30, nullable = false)
    private String password;

    @NotEmpty
    @Column(length = 30, nullable = false)
    private String firstname;

    @NotEmpty
    @Column(length = 30, nullable = false)
    private String lastname;

    @Email
    private String email;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "postalcode")
    private City city;

    private Title title;

    @OneToMany(mappedBy = "username", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AuthoritiesEntity> authoritiesList;

    public User(String username, String password, String firstname, String lastname, String email, City postalcode, Title title, List<Role> roleList) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.city = postalcode;
        this.title = title;
        this.authoritiesList = null;
        for (Role role : roleList) {
            this.setRole(String.valueOf(role));
        }
    }

    public void setRole(String role) {
        if (this.getAuthoritiesList() == null) {
            this.setAuthoritiesList(new ArrayList<>());
        }
        this.getAuthoritiesList().add(new AuthoritiesEntity(this, role));
    }
}
