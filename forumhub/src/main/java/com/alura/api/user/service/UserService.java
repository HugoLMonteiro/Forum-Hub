package com.alura.api.user.service;

import com.alura.api.security.service.JwtService;
import com.alura.api.user.dto.UserRegisterDto;
import com.alura.api.user.dto.UserTokenDto;
import com.alura.api.user.model.User;
import com.alura.api.user.repository.UserRepository;
import com.alura.api.user.util.exception.UserException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterDto data) {
        User entity = new User(data);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return repository.save(entity);
    }

    public UserTokenDto login(Authentication data) {
        return new UserTokenDto(jwtService.encode(data));
    }

    public User readById(UUID id) {
        return repository
            .findById(id)
            .orElseThrow(() -> new UserException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
