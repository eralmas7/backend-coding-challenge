package com.calculator.expense.service;

import java.util.List;
import com.calculator.expense.model.Expense;

/**
 * A service which would be responsible to save expense or retrieve the expense from storage
 * repository.
 */
public interface ExpenseService {

    /**
     * Get all the expense that we have saved till now.
     * 
     * @return
     */
    public List<Expense> getAllExpenses();

    /**
     * Save the expense onto repository.
     * 
     * @param expense
     * @return
     */
    public Expense addExpense(final Expense expense);
}
