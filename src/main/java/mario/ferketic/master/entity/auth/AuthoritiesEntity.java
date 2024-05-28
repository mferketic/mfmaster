package mario.ferketic.master.entity.auth;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mario.ferketic.master.entity.MyEntity;
import mario.ferketic.master.entity.users.User;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "authorities")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CompositeKey.class)
public class AuthoritiesEntity implements MyEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    @JsonBackReference
    private User username;

    @Id
    private String authority;
}
