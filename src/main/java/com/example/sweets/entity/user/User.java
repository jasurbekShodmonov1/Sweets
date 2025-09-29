package com.example.sweets.entity.user;

import com.example.sweets.entity.base.BaseDomain;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseDomain<UUID> implements UserDetails {

  @Column(unique = true)
  private String username;

  private String fullName;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String phoneNumber;

  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id", nullable = false),
      inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
  private List<Role> roles;

  //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  //    private List<Product> products;

  @CreationTimestamp private LocalDateTime createdAt;

  private Boolean enabled = Boolean.FALSE;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.enabled;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.enabled;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.enabled;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }
}
