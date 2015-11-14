package com.calculator.expense.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.calculator.expense.model.Expense;
import com.calculator.expense.service.ExpenseService;

/**
 * Spring's rest controller which will expose rest api's for the expense calculation engine.
 */
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(final ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    /**
     * Get all the expenses submitted by the calculation engine from the storage media.
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Expense> findItems() {
        return expenseService.getAllExpenses();
    }

    /**
     * Add a new expense to the storage media.
     * 
     * @param expense
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Expense addExpense(final @RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }
}
