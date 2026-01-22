package com.portfolio.expensetracker.service;

import com.portfolio.expensetracker.model.Expense;
import com.portfolio.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service Layer per la gestione delle spese.
 * <p>Contiene la business logic e funge da intermediario tra Controller e Repository</p>
 */

@Service //Definisco questo componente come un Bean di servizio nel contesto Spring
@RequiredArgsConstructor //Creo un costruttore con i campi 'final' (Dependency Injection pulita)
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    /**
     * Recupera tutte le liste presenti nel DataBase
     * @return Lista completa delle spese
     */
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    /**
     * Salva una nuova spesa o ne aggiorna una già esistente.
     * Inlude una validazione di business: l'importo deve essere positivo
     * @param expense L'oggetto spesa da salvare.
     * @return la spesa salvata incluso l'id generato
     * @throws IllegalArgumentException se l'importo è negativo o zero.
     */
    public Expense saveExpense(Expense expense) {
        //Controllo se l'importo è null
        if(expense.getAmount() == null){
         throw new IllegalArgumentException("L'importo non può essere nullo");
        }
        // Business Logic: Validazione
        if (expense.getAmount().signum() <= 0) {
            throw new IllegalArgumentException("L'importo della spesa deve essere positivo");
        }
        return expenseRepository.save(expense);
    }

    /**
     * Cancella una spesa dato il suo ID.
     * @param id Identificativo della spesa.
     */
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    /**
     * Trova una spesa in un lasso temporale
     * @param startDate Il periodo di inizio
     * @param endDate Il periodo di fine
     * @return la lista delle spese in quel determinato periodo
     */
    public List<Expense> getExpensesByDate(LocalDate startDate, LocalDate endDate){
        return expenseRepository.findByDateBetween(startDate, endDate);
    }

    /**
     * Trova tutte le spese con la stessa categoria
     * @param category la categoria delle spese
     * @return la lista delle spese con la stessa categoria
     */
    public List<Expense> getExpensesByCategory(String category){
        if(category == null || category.isBlank()){
            throw new IllegalArgumentException("La categoria non può essere vuota!!");
        }
        return expenseRepository.findByCategory(category);
    }

}
