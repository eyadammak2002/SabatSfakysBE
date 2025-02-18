package tn.SabatSfakys.security.services;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.SabatSfakys.model.Role;
import tn.SabatSfakys.model.User;



public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String email;

  @JsonIgnore
  private String password;

  private Role role; // Directly store the role

  public UserDetailsImpl(Long id, String username, String email, String password, Role role) {
      this.id = id;
      this.username = username;
      this.email = email;
      this.password = password;
      this.role = role;
  }
  

  public static UserDetailsImpl build(User user) {
      // Create UserDetailsImpl with a single role
      return new UserDetailsImpl(
          user.getId(),
          user.getUsername(),
          user.getEmail(),
          user.getPassword(),
          user.getRole() // Directly pass the role
          
      );
  }



  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      return Collections.singletonList(new SimpleGrantedAuthority(role.getName().name()));
  }

  public Role getRole() {
      return role;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }


}