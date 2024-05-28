package mario.ferketic.master.service;

import mario.ferketic.master.entity.users.AnnualLeave;

import java.time.LocalDate;

public interface AnnualLeaveService {
    AnnualLeave requestAnnualLeave(String employeeUsername, LocalDate startDate, LocalDate endDate);

    int getDaysLeft(String employeeUsername);

    AnnualLeave approveAnnualLeave(String managerUsername, String employeeUsername);

    AnnualLeave rejectAnnualLeave(String managerUsername, String employeeUsername);
}
