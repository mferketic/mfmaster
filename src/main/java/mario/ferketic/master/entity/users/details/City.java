package mario.ferketic.master.entity.users.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mario.ferketic.master.entity.MyEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements MyEntity {
    @Id
    @Column(name = "postalcode")
    private int postalcode;
    @Column(length = 200, nullable = false)
    private String name;
}
