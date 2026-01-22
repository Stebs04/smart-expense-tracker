package com.portfolio.expensetracker.model;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String description; //Descrizione testuale della spesa.(Campo obbligatorio)

    @Column(nullable = false)
    private BigDecimal amount; //Importo monetario della spesa. Utilizza BigDecimal per la precisione nei calcoli

    @Column(nullable = false)
    private LocalDate date; //Data in cui è stata effettuata la spesa. Utilizza LocalDate (solo giorno, mese, anno).

    @Column
    private String category; //Categoria della spesa
}
