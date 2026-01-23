package com.portfolio.expensetracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Modello che rappresenta una spesa singola nel sistema.
 * Mappato sulla tabella 'expenses' del DataBase relazionale
 * @author Stefano Bellan
 */
@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Identificativo univoco della spesa, generata automaticamente dal DB

    @NotBlank(message = "La descrizione è obbligatoria")
    @Column(nullable = false)
    private String description; //Descrizione testuale della spesa.(Campo obbligatorio)

    @NotBlank(message = "L'importo è obbligatorio")
    @Column(nullable = false)
    private BigDecimal amount; //Importo monetario della spesa. Utilizza BigDecimal per la precisione nei calcoli

    @NotBlank(message = "La data è obbligatoria")
    @Column(nullable = false)
    private LocalDate date; //Data in cui è stata effettuata la spesa. Utilizza LocalDate (solo giorno, mese, anno).

    @NotBlank(message = "La Categoria è obbligatoria")
    @Column
    private String category; //Categoria della spesa

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id", nullable = false)
    private  User user; //L'utente che effettua la spesa
}
