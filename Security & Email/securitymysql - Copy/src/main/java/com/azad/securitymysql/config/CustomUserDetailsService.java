package com.azad.securitymysql.config;

import com.azad.securitymysql.entity.User;
import com.azad.securitymysql.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String usernameoremail) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByUsernameOrEmail(usernameoremail, usernameoremail);
        optionalUser
                .orElseThrow(()-> new UsernameNotFoundException("UIsername not found"));

        return optionalUser
                .map(CustomUserDetails::new).get();
    }
}
