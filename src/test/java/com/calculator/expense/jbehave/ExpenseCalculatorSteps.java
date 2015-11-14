package com.calculator.expense.jbehave;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import com.calculator.expense.model.Expense;
import com.calculator.expense.service.ExpenseService;

@Steps
public class ExpenseCalculatorSteps {

    @Autowired
    private ExpenseService expenseService;
    private Expense expense;
    private List<Expense> expenseList;

    @Given("a user desires to create new expense")
    public void createNewExpense() {
        expense = new Expense();
    }

    @When("user tries to create an expense of amount $amount with vat amount $vatAmount with reason $reason")
    public void populateExpenseData(String amount, String vatAmount, String reason) {
        expense.setAmount(new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP));
        expense.setVatAmount(new BigDecimal(vatAmount).setScale(2, RoundingMode.HALF_UP));
        expense.setReason(reason);
        expense.setDate(new Date());
    }

    @Then("user should be allowed to create an expense")
    public void registerExpense() {
        final Expense expense = expenseService.addExpense(this.expense);
        Assert.assertEquals(this.expense.getReason(), expense.getReason());
        Assert.assertEquals(this.expense.getAmount(), expense.getAmount());
        Assert.assertEquals(this.expense.getVatAmount(), expense.getVatAmount());
    }

    @Given("a user desires to view existing expense")
    public void viewExpenseDesire() {
        System.out.println("User opens up browser");
    }

    @When("user tries to view all his/her expense")
    public void tryGetExpenseData() {
        expenseList = expenseService.getAllExpenses();
    }

    @Then("user should be get all the expenses that were created")
    public void viewExpenses() {
        Assert.assertEquals(1, this.expenseList.size());
        for (Expense expense : expenseList) {
            Assert.assertEquals(this.expense.getReason(), expense.getReason());
            Assert.assertEquals(this.expense.getAmount(), expense.getAmount());
            Assert.assertEquals(this.expense.getVatAmount(), expense.getVatAmount());
        }
    }
}
