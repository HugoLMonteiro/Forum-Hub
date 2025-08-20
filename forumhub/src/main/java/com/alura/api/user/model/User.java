package com.alura.api.user.model;

import com.alura.api.user.dto.UserRegisterDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", unique = true, nullable = false, length = 35)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public User(@NonNull UserRegisterDto data) {
        this.username = data.username();
        this.password = data.password();
        this.role = Role.BASE_USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role.name()));
    }
}
