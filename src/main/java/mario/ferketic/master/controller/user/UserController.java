package mario.ferketic.master.controller.user;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.dto.users.UserDto;
import mario.ferketic.master.enums.Role;
import mario.ferketic.master.exceptions.ForbiddenException;
import mario.ferketic.master.mapper.UserMapper;
import mario.ferketic.master.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok().body(userService.getAllUsers().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
        var user = userMapper.toDto(userService.getByUsername(username));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        disableOwnerManagerSave(userDto);
        var savedUser = userService.createUser(userMapper.toEntity(userDto));
        return ResponseEntity.accepted().body(userMapper.toDto(savedUser));
    }

    @PatchMapping()
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        disableOwnerManagerSave(userDto);
        var savedUser = userService.updateUser(userMapper.toEntity(userDto));
        return ResponseEntity.accepted().body(userMapper.toDto(savedUser));
    }

    private static void disableOwnerManagerSave(UserDto userDto) {
        for (Role role : userDto.getRoleList()) {
            if (role.toString().equals("ROLE_OWNER") || role.toString().equals("ROLE_MANAGER")) {
                throw new ForbiddenException("You can't promote others to Owner or Manager role.");
            }
        }
    }

}
