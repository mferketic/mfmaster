package mario.ferketic.master.service.impl;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.entity.users.AnnualLeave;
import mario.ferketic.master.entity.users.User;
import mario.ferketic.master.exceptions.BadRequestException;
import mario.ferketic.master.exceptions.ConflictException;
import mario.ferketic.master.repository.users.AnnualLeaveRepository;
import mario.ferketic.master.service.AnnualLeaveService;
import mario.ferketic.master.service.ManagementService;
import mario.ferketic.master.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnualLeaveServiceImpl implements AnnualLeaveService {

    private final AnnualLeaveRepository annualLeaveRepository;
    private final UserService userService;
    private final ManagementService managementService;
    private static final int MAX_NUMBER_OF_DAYS = 22;

    @Override
    public AnnualLeave requestAnnualLeave(String employeeUsername, LocalDate startDate, LocalDate endDate) {
        var employee = userService.getByUsername(employeeUsername);
        var annualLeavesOfEmployeeList = annualLeaveRepository.findAnnualLeavesByEmployee(employee);
        int daysLeft = calculateDaysLeft(annualLeavesOfEmployeeList);
        if (endDate.isBefore(startDate)) {
            throw new BadRequestException("End date cannot be before start date.");
        }
        if (daysLeft < (int) ChronoUnit.DAYS.between(startDate, endDate)) {
            throw new BadRequestException("Not enough days left for annual leave.");
        }
        for (AnnualLeave annualLeave : annualLeavesOfEmployeeList) {
            if (!annualLeave.isVerified()) {
                throw new ConflictException("Employee already has unverified annual leave.");
            }
        }
        AnnualLeave annualLeave = new AnnualLeave();
        annualLeave.setEmployee(employee);
        annualLeave.setStart(startDate);
        annualLeave.setEnd(endDate);
        return annualLeaveRepository.save(annualLeave);
    }

    @Override
    public int getDaysLeft(String employeeUsername) {
        var employee = userService.getByUsername(employeeUsername);
        var annualLeavesOfEmployeeList = annualLeaveRepository.findAnnualLeavesByEmployee(employee);
        return calculateDaysLeft(annualLeavesOfEmployeeList);
    }

    private int calculateDaysLeft(List<AnnualLeave> annualLeavesOfEmployeeList) {
        int daysLeft = MAX_NUMBER_OF_DAYS;
        for (AnnualLeave annualLeave : annualLeavesOfEmployeeList) {
            daysLeft -= (int) ChronoUnit.DAYS.between(annualLeave.getStart(), annualLeave.getEnd());
        }
        return daysLeft;
    }

    @Override
    public AnnualLeave approveAnnualLeave(String managerUsername, String employeeUsername) {
        managementService.checkIfManagerManagesEmployee(managerUsername, employeeUsername);
        var employee = userService.getByUsername(employeeUsername);
        var annualLeavesOfEmployeeList = annualLeaveRepository.findAnnualLeavesByEmployee(employee);
        for (AnnualLeave annualLeave : annualLeavesOfEmployeeList) {
            if (!annualLeave.isVerified()) {
                annualLeave.setVerified(true);
            }
            return annualLeaveRepository.save(annualLeave);
        }
        throw new ConflictException("No annual leave requests to approve.");
    }

    @Override
    public AnnualLeave rejectAnnualLeave(String managerUsername, String employeeUsername) {
        managementService.checkIfManagerManagesEmployee(managerUsername, employeeUsername);
        var employee = userService.getByUsername(employeeUsername);
        var annualLeavesOfEmployeeList = annualLeaveRepository.findAnnualLeavesByEmployee(employee);
        for (AnnualLeave annualLeave : annualLeavesOfEmployeeList) {
            if (!annualLeave.isVerified()) {
                annualLeaveRepository.delete(annualLeave);
                return annualLeave;
            }
        }
        throw new ConflictException("No annual leave requests to reject.");
    }
}
