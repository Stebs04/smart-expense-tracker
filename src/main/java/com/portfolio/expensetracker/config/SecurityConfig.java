package com.portfolio.expensetracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Questo metodo restituisce una istanza di {@link BCryptPasswordEncoder} utilizzato per l'autenticazione
     * @return l'istanza configurata di {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura la catena di filtri di sicurezza
     * @param http l'oggetto {@link HttpSecurity} utilizzato per la configurazione di sicurezza web
     * @return Il bean {@link SecurityFilterChain} costruito che gestisce la sicurezza delle richieste HTTP in entrata.
     * @throws Exception e si verificano errori durante la configurazione delle regole di sicurezza HTTP.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        //PAGINE PUBBLICHE
                        .requestMatchers("/register", "/save-user", "/login", "/css/**", "/js/**").permitAll()
                        //TUTTO IL RESTO, Richiede autenticazione
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        //Se il login va a buon fine, redirect su expense
                        .defaultSuccessUrl("/expense", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        //Effettuato il logout redirect a login
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
}
