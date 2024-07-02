package service;

import abstraction.OwnerService;
import dto.OwnerDto;
import dto.UserDto;
import entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@RequiredArgsConstructor
@ComponentScan(basePackages = {"repository", "config"})
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final OwnerService ownerService;

    private final PasswordEncoder passwordEncoder = passwordEncoder();

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDetails signUp(UserDto data) {
        if (userRepository.findByUsername(data.getUsername()) != null) {
            throw new UsernameNotFoundException("username already exists");
        }
        String encryptedPassword = passwordEncoder.encode(data.getPassword());
        OwnerDto owner = new OwnerDto();
        // var ownerInDb = ownerService.createOwner(owner);
        //  UserEntity newUser = new UserEntity(data.getUsername(), encryptedPassword, ownerInDb.getId(), data
        //  .getRoles());
        // todo
        UserEntity newUser = new UserEntity(data.getUsername(), encryptedPassword, 13L, data.getRoles());
        return userRepository.save(newUser);
    }
}
