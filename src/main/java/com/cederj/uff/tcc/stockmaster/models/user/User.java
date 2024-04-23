package com.cederj.uff.tcc.stockmaster.models.user;

import com.cederj.uff.tcc.stockmaster.models.GenericModel;
import com.cederj.uff.tcc.stockmaster.models.inventory.Inventory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends GenericModel implements UserDetails {

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String userName;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @OneToMany
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private List<Inventory> inventories = new ArrayList<>();

  public User(String name, String userName, String email, String password) {
    this.name = name;
    this.userName = userName;
    this.email = email;
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return userName;
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