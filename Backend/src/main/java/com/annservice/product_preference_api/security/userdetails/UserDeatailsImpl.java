package com.annservice.product_preference_api.security.userdetails;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.annservice.product_preference_api.entity.UserInfo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDeatailsImpl implements UserDetails {
    private final UserInfo userInfo;

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = userInfo.getAuthorities().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
                .toList();
        return authorities;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUserId();
    }

    public String getEmail() {
        return userInfo.getEmail();
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
