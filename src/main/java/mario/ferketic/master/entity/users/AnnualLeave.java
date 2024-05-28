package mario.ferketic.master.entity.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AnnualLeave {
    @Id
    private int id;
    private LocalDate start;
    private LocalDate end;
    private boolean verified;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;
}
