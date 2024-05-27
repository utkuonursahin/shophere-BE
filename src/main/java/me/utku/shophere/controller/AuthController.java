package me.utku.shophere.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.utku.shophere.dto.GenericResponse;
import me.utku.shophere.dto.LoginRequest;
import me.utku.shophere.dto.SignupRequest;
import me.utku.shophere.dto.UserDto;
import me.utku.shophere.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<GenericResponse<UserDto>> signup(@RequestBody SignupRequest signupRequest){
        UserDto userDto = authService.signup(signupRequest);
        GenericResponse<UserDto> response = new GenericResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                userDto
        );
        return response.toResponseEntity();
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<Boolean>> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        GenericResponse<Boolean> authResponse = authService.authenticate(loginRequest, request ,response);
        return authResponse.toResponseEntity();
    }
}
