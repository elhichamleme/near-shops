package com.unitedremote.nearshops.model.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String email;
    private String password;
    private Set<Shop> preferredShops = new HashSet<>();
    private Set<Shop> dislikedShops = new HashSet<>();
    private List<String> roles = new ArrayList<>();

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Shop> getPreferredShops() {
        return preferredShops;
    }

    public void setPreferredShops(Set<Shop> preferredShops) {
        this.preferredShops = preferredShops;
    }

    public Set<Shop> getDislikedShops() {
        return dislikedShops;
    }

    public void setDislikedShops(Set<Shop> dislikedShops) {
        this.dislikedShops = dislikedShops;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.email;
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
