package com.calculator.expense;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.calculator.expense.model.Expense;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Bootstrap.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
@DirtiesContext
public class ExpenseCalculatorTest {

    private static final String EXPENSE_URL = "http://localhost:9000/expenses";
    private static final String EXPENSE_REASON = "Team Lunch";

    private Expense createExpense() {
        final Expense expense = new Expense();
        expense.setAmount(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP));
        expense.setVatAmount(new BigDecimal(16.67).setScale(2, RoundingMode.HALF_UP));
        expense.setDate(new Date());
        expense.setReason(EXPENSE_REASON);
        return expense;
    }

    private void assertAfterPostExpense(final RestTemplate restTemplate, final Expense expense) {
        final ResponseEntity<Expense> expenseEntity = restTemplate.postForEntity(EXPENSE_URL, expense, Expense.class);
        assertTrue(expenseEntity.hasBody());
        assertTrue(expenseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(expenseEntity.getBody().getReason(), EXPENSE_REASON);
    }

    private void assertAfterGetExpense(final RestTemplate restTemplate) {
        final ResponseEntity<List<Expense>> expenseListEntity = restTemplate.exchange(EXPENSE_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Expense>>() {});
        assertTrue(expenseListEntity.getStatusCode().is2xxSuccessful());
        assertTrue(expenseListEntity.hasBody());
        assertEquals(1, expenseListEntity.getBody().size());
        assertEquals(expenseListEntity.getBody().get(0).getReason(), EXPENSE_REASON);
    }

    @Test
    public void testExpenseResourceWithValidAuthentication() {
        final RestTemplate restTemplate = new TestRestTemplate("stanley.kubrick@gmail.com", "123456");
        final Expense expense = createExpense();
        assertAfterPostExpense(restTemplate, expense);
        assertAfterGetExpense(restTemplate);
    }

    @Test(expected = ResourceAccessException.class)
    public void testPostExpenseDataWithoutAuthentication() {
        final RestTemplate restTemplate = new TestRestTemplate();
        final Expense expense = createExpense();
        restTemplate.postForEntity(EXPENSE_URL, expense, String.class);
    }

    @Test
    public void testGetAllExpenseDataWithoutAuthentication() {
        final RestTemplate restTemplate = new TestRestTemplate();
        final ResponseEntity<String> expenseEntity = restTemplate.getForEntity(EXPENSE_URL, String.class);
        assertTrue(expenseEntity.getStatusCode().is4xxClientError());
    }
}
