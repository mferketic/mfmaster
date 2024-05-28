package mario.ferketic.master.mapper;

import mario.ferketic.master.dto.users.UserDto;
import mario.ferketic.master.entity.users.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements GenericMapper<User, UserDto> {

    @Override
    public User toEntity(UserDto dto) {
        return new User(dto.getUsername(), dto.getPassword(),
                dto.getFirstname(), dto.getLastname(), dto.getEmail(),
                dto.getCity(), dto.getTitle(), dto.getRoleList());
    }

    @Override
    public UserDto toDto(User entity) {
        return new UserDto(entity.getUsername(), entity.getPassword(),
                entity.getFirstname(), entity.getLastname(), entity.getEmail(),
                entity.getCity(), entity.getTitle(), entity.getAuthoritiesList());
    }
}
