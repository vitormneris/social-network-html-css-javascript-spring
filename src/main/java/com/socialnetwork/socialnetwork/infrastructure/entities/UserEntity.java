package com.socialnetwork.socialnetwork.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialnetwork.socialnetwork.infrastructure.entities.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
public class UserEntity implements Serializable, UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 50,  unique = true, nullable = false)
    private String username;

    @Column(length = 254, unique = true, nullable = false)
    private String login;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 254, unique = true)
    private String urlImage;

    @Column(length = 30, nullable = false)
    private UserRole role;

    @JsonIgnore
    @Column(nullable = false)
    @OneToMany(mappedBy = "author")
    private List<PublicationEntity> publications = new ArrayList<>();


    public UserEntity(Long id, String username, String login, String password, UserRole role) {
        this.username = username;
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
