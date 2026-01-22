package com.portfolio.expensetracker.controller;

import com.portfolio.expensetracker.model.Expense;
import com.portfolio.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Controller MVC che gestisce le richieste web per le spese.
 * Gestisce visualizzazione, creazione, ricerca e cancellazione.
 */
@Controller
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    /**
     * Gestisce la richiesta GET sulla pagina principale.
     * Mostra l'elenco completo delle spese.
     */
    @GetMapping
    public String viewHomePage(Model model) {
        var listExpenses = expenseService.getAllExpenses();
        // Nota: uso il plurale "expenses" perché è una lista
        model.addAttribute("expenses", listExpenses);
        return "expense-list";
    }

    /**
     * Mostra il form per creare una nuova spesa.
     */
    @GetMapping("/new")
    public String showNewExpenseForm(Model model) {
        Expense expense = new Expense();
        // Qui uso il singolare "expense" perché è un oggetto solo
        model.addAttribute("expense", expense);
        return "create-expense";
    }

    /**
     * Salva la spesa ricevuta dal form.
     */
    @PostMapping("/save")
    public String saveExpense(@ModelAttribute("expense") Expense expense) {
        expenseService.saveExpense(expense);
        return "redirect:/expense";
    }

    /**
     * Elimina una spesa tramite ID passato nell'URL.
     * Esempio URL: /expenses/delete/15
     * @param id L'ID preso dal percorso (Path Variable)
     */
    @GetMapping("/delete/{id}") // Nota le parentesi graffe {id}
    public String deleteExpense(@PathVariable(value = "id") Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expense";
    }

    /**
     * Cerca spese per categoria.
     * Esempio URL: /expenses/search-category?category=Cibo
     * @param category Il parametro preso dalla query string
     */
    @GetMapping("/search-category")
    public String showCategoryExpenses(@RequestParam("category") String category, Model model) {
        var listExpenses = expenseService.getExpensesByCategory(category);
        // Riutilizziamo la STESSA vista della home page, ma con la lista filtrata!
        model.addAttribute("expenses", listExpenses);
        return "expense-list";
    }

    /**
     * Cerca spese per data.
     * Esempio URL: /expenses/search-date?startDate=2026-01-01&endDate=2026-01-31
     */
    @GetMapping("/search-date")
    public String showDateExpenses(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {

        var listExpenses = expenseService.getExpensesByDate(startDate, endDate);
        model.addAttribute("expenses", listExpenses);
        return "expense-list";
    }
}