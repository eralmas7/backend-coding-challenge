package com.calculator.expense.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.calculator.expense.model.Expense;
import com.calculator.expense.repository.ExpenseRepository;

/**
 * Service to provide answer to expense related queries i.e. all expense or save expense.
 */
@Service
public class ExpenseDataService implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseDataService(final ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense addExpense(final Expense expense) {
        return expenseRepository.saveAndFlush(expense);
    }
}
