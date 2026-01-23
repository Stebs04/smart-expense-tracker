package com.portfolio.expensetracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


/**
 * Rappresenta l'utente che accede al Sistema
 */
@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Chiave Primaria

    //DATI OBBLIGATORI

    @NotBlank(message = "Il nome è obbligatorio")
    @Column(nullable = false)
    private String firstName; //Il nome dell'utente

    @NotBlank(message = "il cognome è obbligatorio")
    @Column(nullable = false)
    private String lastName; //Cognome dell'utente

    // EMAIL (Deve essere univoca per il login)
    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Formato email non valido")
    @Column(nullable = false, unique = true)
    private String email;

    // PASSWORD (verrà salvata cifrata)
    @NotBlank(message = "La password è obbligatoria")
    @Column(nullable = false)
    private String password;

    // DATI FACOLTATIVI
    private Integer age;

    // RELAZIONE CON LE SPESE
    // Un utente -> Molte spese
    // mappedBy = "user" fa riferimento al campo 'user' che scriveremo ora dentro Expense.java
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Expense> expenses;

}
