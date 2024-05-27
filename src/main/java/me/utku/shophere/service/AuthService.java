package me.utku.shophere.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.utku.shophere.dto.GenericResponse;
import me.utku.shophere.dto.LoginRequest;
import me.utku.shophere.dto.SignupRequest;
import me.utku.shophere.dto.UserDto;
import me.utku.shophere.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    public UserDto signup(SignupRequest signupRequest){
        User createdUser = userService.create(signupRequest);
        return new UserDto(
                createdUser.getName(),
                createdUser.getSurname(),
                createdUser.getEmail(),
                createdUser.getBirthDate()
        );
    }

    public GenericResponse<Boolean> authenticate(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        GenericResponse<Boolean> authResponse = null;
        try{
            UsernamePasswordAuthenticationToken authToken = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(),loginRequest.password());
            Authentication authentication = authenticationManager.authenticate(authToken);
            if (authentication.isAuthenticated()) {
                SecurityContext context = securityContextHolderStrategy.createEmptyContext();
                context.setAuthentication(authentication);
                securityContextHolderStrategy.setContext(context);
                securityContextRepository.saveContext(context, request, response);
                authResponse = new GenericResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),true);
            }
        }catch (Exception e){
            throw new BadCredentialsException("Failed authentication with EMAIL:"+loginRequest.email());
        }
        return authResponse;
    }
}
