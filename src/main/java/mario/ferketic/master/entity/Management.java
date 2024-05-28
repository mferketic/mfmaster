package mario.ferketic.master.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mario.ferketic.master.entity.auth.AuthoritiesEntity;
import mario.ferketic.master.entity.users.User;
import mario.ferketic.master.exceptions.ConflictException;

import javax.persistence.*;

@Entity
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Management implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;

    public Management(User manager, User employee) {
        var managerRoles = manager.getAuthoritiesList().stream().map(AuthoritiesEntity::getAuthority).toList();
        if (!(managerRoles.contains("ROLE_OWNER") || managerRoles.contains("ROLE_MANAGER")))
            throw new ConflictException(String.format("Required ROLE_MANAGER or ROLE_OWNER, provided %s", managerRoles));
        this.manager = manager;

        var employeeRoles = employee.getAuthoritiesList().stream().map(AuthoritiesEntity::getAuthority).toList();
        if (!employeeRoles.contains("ROLE_EMPLOYEE"))
            throw new ConflictException(String.format("Required ROLE_EMPLOYEE, provided %s", managerRoles));
        this.employee = employee;
    }

}
