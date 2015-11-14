package com.calculator.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.calculator.expense.model.Expense;

/**
 * Repository which will be used to store expenses to storage media.
 */
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
