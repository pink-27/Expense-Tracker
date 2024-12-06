package com.example.MiniSplitwise.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Entity;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@Table(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private UUID userId;

    @Column(name="acc_password")
    private String accPassword;

    @Column(name = "name")
    private String name;

    @Column(name = "contact", unique = true)
    private String contact;

    @Column(name = "personal_email", unique = true)
    private String personalEmail;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getAccPassword() {
        return accPassword;
    }

    public void setAccPassword(String accPassword) {
        this.accPassword = accPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return this.personalEmail;
    }

    @Override
    public String getPassword() {
        return this.accPassword;
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

    public boolean isEnabled() {
        return true;
    }
}