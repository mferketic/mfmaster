package mario.ferketic.master.service;

import mario.ferketic.master.entity.Management;

public interface ManagementService {
    Management createManagerForEmployee(String managerUsername, String employeeUsername);

    Management updateManagerForEmployee(String managerUsername, String employeeUsername);

    void checkIfManagerManagesEmployee(String managerUsername, String employeeUsername);
}
