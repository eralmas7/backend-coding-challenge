package com.calculator.expense.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.calculator.expense.model.Expense;
import com.calculator.expense.repository.ExpenseRepository;

public class ExpenseDataServiceTest {

    private ExpenseService expenseService;
    private ExpenseRepository expenseRepository;

    @Before
    public void setUp() throws Exception {
        expenseRepository = mock(ExpenseRepository.class);
        expenseService = new ExpenseDataService(expenseRepository);
    }

    @Test
    public void test_WhenDataIsInRepository_GetReturnEmptyList() {
        final List<Expense> expensesList = new ArrayList<>();
        expensesList.add(mock(Expense.class));
        when(expenseRepository.findAll()).thenReturn(expensesList);
        final List<Expense> expenseList = expenseService.getAllExpenses();
        assertEquals(expenseList, expensesList);
    }

    @Test
    public void test_WhenNoDataInRepository_GetReturnEmptyList() {
        final List<Expense> emptyList = new ArrayList<>();
        when(expenseRepository.findAll()).thenReturn(emptyList);
        final List<Expense> expenseList = expenseService.getAllExpenses();
        assertEquals(expenseList, emptyList);
    }

    @Test(expected = NullPointerException.class)
    public void test_WhenRepositoryThrowsException_GetThrowsException() {
        when(expenseRepository.findAll()).thenThrow(new NullPointerException());
        expenseService.getAllExpenses();
    }

    @Test
    public void test_WhenNewAddExpenseIsRequested_RepositoryAcceptsData() {
        final Expense expense = mock(Expense.class);
        when(expenseRepository.saveAndFlush(expense)).thenReturn(expense);
        final Expense expenseReturned = expenseService.addExpense(expense);
        assertEquals(expenseReturned, expense);
    }

    @Test(expected = NullPointerException.class)
    public void test_WhenRepositoryThrowsException_AddThrowsException() {
        final Expense expense = mock(Expense.class);
        when(expenseRepository.saveAndFlush(expense)).thenThrow(new NullPointerException());
        expenseService.addExpense(expense);
    }
}
