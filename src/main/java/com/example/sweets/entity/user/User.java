//package com.example.sweets.entity.user;
//
//import com.example.sweets.entity.base.BaseDomain;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.*;
//
//
//@Entity
//@Getter
//@Setter
//public class User extends BaseDomain<UUID> implements UserDetails {
//
//    @Column(unique = true)
//    private String username;
//    private String password;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private Profile profile;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
//    private Set<Role> roles;
//
//    private Boolean enabled = Boolean.TRUE;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return this.enabled;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return this.enabled;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return this.enabled;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.enabled;
//    }
//}
