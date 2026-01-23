package com.portfolio.expensetracker.controller;

import com.portfolio.expensetracker.model.User;
import com.portfolio.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller MVC che gestisce l'autenticazione di un utente
 * Gestisce Signin e Login
 */
@Controller
@RequiredArgsConstructor // Lombok crea il costruttore automaticamente
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Metodo che mostra il form per la registrazione
     * @return l'etichetta per la pagina contente il form di registrazione
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        //Creo un utente vuoto da passare all'HTML che riempirà i campi vuoti di questo oggetto
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    /**
     * Metodo che si occupa di salvar un nuovo utente nel sistema
     * @param user l'oggetto {@link User} da registrare nel sistema
     * @return il redirect al login
     */
    @PostMapping("/save-user")
    public String registerUser(@ModelAttribute("user") User user, Model model){
        //Controllo dei duplicati
        User existing = userRepository.findByEmail(user.getEmail());
        if(existing != null){
            // Se esiste, redirect alla pagina di registrazione con un errore
            model.addAttribute("error", "Email già registrata!");
            return "register";
        }
        //Hashing password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //Salvataggio dell'utente nel DB
        userRepository.save(user);

        //Se tutto ok redirect al login
        return "redirect:/login?success";
    }

    /**
     * Metodo che gestiste il login
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
