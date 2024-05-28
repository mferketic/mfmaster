package mario.ferketic.master.entity.project;


import lombok.Data;
import mario.ferketic.master.entity.users.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ProjectWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_work_id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_work_id")
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public void addDeveloper(User employee) {
        if (users == null || users.isEmpty()) {
            users = new ArrayList<>();
        }
        users.add(employee);
    }
}
