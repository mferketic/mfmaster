package mario.ferketic.master.entity.project;

import lombok.Data;
import mario.ferketic.master.entity.MyEntity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Project implements MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;

    @NotEmpty
    @Column(length = 30, nullable = false)
    private String name;

    @NotNull
    @Column(length = 30, nullable = false)
    private int budget;

    @Future
    @Column(length = 30, nullable = false)
    private LocalDate finish_date;
}
