package mario.ferketic.master.controller.auth;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.dto.users.UserAuthDto;
import mario.ferketic.master.service.security.MyUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<MyUserDetails> authenticateUser(UserAuthDto userAuthDto) {

        System.out.println("userDto AC:" + userAuthDto);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuthDto.getUsername(), userAuthDto.getPassword()));
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        System.out.println("user details: " + userDetails.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }
}
