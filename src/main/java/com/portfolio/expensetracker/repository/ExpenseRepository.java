package com.portfolio.expensetracker.repository;

import com.portfolio.expensetracker.model.Expense;
import com.portfolio.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository per la gestione della persistenza delle entità Expense
 * Estende JpaRepository per ereditare i metodi CRUD standard (save, findAll, delete, ecc.).
 * @author Stefano Bellan
 */
@Repository // Indico a Spring che questa interfaccia è un componente DAO
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

    /**
     * Trova tutte le spese appartenenti a una specifica categoria.
     * <p>Spring Data JPA genererà automaticamente le query </p>
     * <p>SELECT * FROM expenses WHERE category = ? </p>
     * @param category La categoria da cercare
     * @return Lista di spese trovate
     */
    List<Expense> findByCategory(String category);

    /**
     * Trova tutte le spese effettuate tra due date.
     * <p> Genera SQL: SELECT * FROM expenses WHERE date BETWEEN ? AND ? </p>
     * @param startDate Data di inizio
     * @param endDate Data di fine
     * @return Lista di spese nel range temporale
     */
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Trova tutte le spese effettuate da un singolo utente
     * <p> Genera SQL: SELECT * FROM expenses WHERE user_id = ?</p>
     * @param user {@link User} l'utente che ha effettutato la spesa
     * @return una lista {@link List} di tutte le spese effettuate da un utente
     */
    List<Expense> findByUser(User user);
}
