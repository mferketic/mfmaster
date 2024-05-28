package mario.ferketic.master.service.impl;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.entity.Management;
import mario.ferketic.master.entity.users.User;
import mario.ferketic.master.enums.Role;
import mario.ferketic.master.exceptions.ConflictException;
import mario.ferketic.master.repository.users.ManagementRepository;
import mario.ferketic.master.service.ManagementService;
import mario.ferketic.master.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagementServiceImpl implements ManagementService {

    private final ManagementRepository managementRepository;
    private final UserService userService;

    @Override
    public Management createManagerForEmployee(String managerUsername, String employeeUsername) {
        var manager = userService.getByUsername(managerUsername);
        var employee = userService.getByUsername(employeeUsername);
        checkRoles(manager, employee);
        if (managementRepository.findManagementByEmployee(employee).isPresent()) {
            throw new ConflictException(String.format("Employee %s already has a manager!", employee.getUsername()));
        }
        return managementRepository.save(new Management(manager, employee));
    }

    @Override
    public Management updateManagerForEmployee(String managerUsername, String employeeUsername) {
        var manager = userService.getByUsername(employeeUsername);
        var employee = userService.getByUsername(employeeUsername);
        checkRoles(manager, employee);
        var managementByEmployee = managementRepository.findManagementByEmployee(employee);
        if (managementByEmployee.isEmpty()) {
            throw new ConflictException("Employee doesn't yet have a manager!");
        }
        if (managementRepository.findByManagerAndEmployee(manager, employee).isPresent()) {
            throw new ConflictException("Manager is already in management for that Employee!");
        }
        managementByEmployee.get().setEmployee(employee);
        managementByEmployee.get().setManager(manager);
        return managementRepository.save(managementByEmployee.get());
    }

    @Override
    public void checkIfManagerManagesEmployee(String managerUsername, String employeeUsername) {
        var manager = userService.getByUsername(managerUsername);
        var employee = userService.getByUsername(employeeUsername);
        checkRoles(manager, employee);
        if (managementRepository.findByManagerAndEmployee(manager, employee).isEmpty()) {
            throw new ConflictException("Manager does not manage Employee!");
        }
    }

    private void checkRoles(User manager, User employee) {
        if (!userHasRole(manager, Role.ROLE_MANAGER)) {
            throw new ConflictException("Manager does not have manager role!");
        }
        if (!userHasRole(employee, Role.ROLE_EMPLOYEE)) {
            throw new ConflictException("Employee does not have developer role!");
        }
    }

    private boolean userHasRole(User manager, Role role) {
        for (var authEntity : manager.getAuthoritiesList()) {
            if (authEntity.getAuthority().contains(role.toString())) {
                return true;
            }
        }
        return false;
    }
}
