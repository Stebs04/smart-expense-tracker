package com.portfolio.expensetracker.service;

import com.portfolio.expensetracker.model.User;
import com.portfolio.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        //Cerco l'utente nel Database usando la sua mail
        User user = userRepository.findByEmail(email);

        //Se non esiste lanciamo l'errore che Spring si aspetta
        if(user == null){
            throw new UsernameNotFoundException("Utente non trovato con email: " + email);
        }

        //Se esiste dobbiamo trasformare l'oggetto User in un oggetto UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), //Username
                user.getPassword(), //Password Hashata
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) //Ruolo
        );
    }
}
