package com.lenden.rbac.security;

import com.lenden.rbac.models.User;
import com.lenden.rbac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        User user = iUserRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found !"));
        return user;
    }


}
