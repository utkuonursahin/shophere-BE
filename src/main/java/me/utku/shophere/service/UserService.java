package me.utku.shophere.service;

import lombok.RequiredArgsConstructor;
import me.utku.shophere.dto.SignupRequest;
import me.utku.shophere.enums.Role;
import me.utku.shophere.model.User;
import me.utku.shophere.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<User> getUserByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    public User create(SignupRequest signupRequest){
        User newUser = User.builder()
                .name(signupRequest.name())
                .surname(signupRequest.surname())
                .email(signupRequest.email())
                .password(bCryptPasswordEncoder.encode(signupRequest.password()))
                .birthDate(signupRequest.birthDate())
                .authorities(Set.of(Role.ROLE_USER))
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
