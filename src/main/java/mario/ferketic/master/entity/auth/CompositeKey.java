package mario.ferketic.master.entity.auth;

import lombok.EqualsAndHashCode;
import mario.ferketic.master.entity.users.User;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
public class CompositeKey implements Serializable {
    private User username;
    private String authority;
}
