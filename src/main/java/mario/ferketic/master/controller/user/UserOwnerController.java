package mario.ferketic.master.controller.user;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.dto.users.UserDto;
import mario.ferketic.master.mapper.UserMapper;
import mario.ferketic.master.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("owner")
@RequiredArgsConstructor
public class UserOwnerController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> addUserAsOwner(@Valid @RequestBody UserDto userDto) {
        var savedUser = userService.createUser(userMapper.toEntity(userDto));
        return ResponseEntity.accepted().body(userMapper.toDto(savedUser));
    }

    @PatchMapping("/{username}")
    public ResponseEntity<UserDto> updateUserAsOwner(@Valid @RequestBody UserDto userDto) {
        var savedUser = userService.updateUserAsOwner(userMapper.toEntity(userDto));
        return ResponseEntity.accepted().body(userMapper.toDto(savedUser));
    }

}
