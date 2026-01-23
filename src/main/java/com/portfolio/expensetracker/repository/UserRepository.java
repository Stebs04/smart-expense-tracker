package com.portfolio.expensetracker.repository;

import com.portfolio.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository per la gestione della persistenza delle entità Expense
 * Estende JpaRepository per ereditare i metodi CRUD standard (save, findAll, delete, ecc.).
 * @author Stefano Bellan
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Metodo che ritorna l'utente attraverso la sua mail
     * @param email l'email dell'utente da ricercare nel database
     * @return {@link User} un utente
     */
    User findByEmail(String email);
}
