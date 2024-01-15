package model;

import exceptions.InsufficientCredit;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import exceptions.InvalidCreditRange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserStepDefinitions {
    private User user;
    private float initialCredit;

    @Given("the user has a credit of {float}")
    public void theUserHasACreditOf(float initialCredit) {
        this.user = new User("maryam", "1234", "maryam@gmail.com", "27/11/2001", "address");
        this.user.setCredit(initialCredit);
        this.initialCredit = initialCredit;
    }

    @When("the user adds {float} credit")
    public void theUserAddsCredit(float addedCredit) throws InvalidCreditRange {
        user.addCredit(addedCredit);
    }

    @Then("the user's credit should be {float}")
    public void theUserCreditShouldBe(float expectedCredit) {
        assertEquals(expectedCredit, user.getCredit(), 0.001);
    }

    @When("an attempt is made to add credit of {float}")
    public void theUserAddsCreditInvalidRange(float amount) {
        Throwable exception = assertThrows(InvalidCreditRange.class, () -> user.addCredit(amount));
        assertEquals("Credit value must be a positive float", exception.getMessage());
    }

    @Then("an InvalidCreditRange exception should be thrown")
    public void nothing(){

    }

    @When("the user withdraws {float} credit")
    public void theUserWithdrawsCredit(float amount) throws InsufficientCredit {
        user.withdrawCredit(amount);
    }

    @Then("the user should have a remained credit of {float}")
    public void theUserShouldHaveATotalCreditOf(float expectedCredit) {
        assertEquals(expectedCredit, user.getCredit());
    }

    @When("the user withdraws more than it's credit like {float}")
    public void theUserWithdrawsCreditInsufficient(float amount) {
        Throwable exception = assertThrows(InsufficientCredit.class, () -> user.withdrawCredit(amount));
        assertEquals("Credit is insufficient.", exception.getMessage());
    }

    @Then("an InsufficientCredit exception should be thrown")
    public void nothing2(){

    }
}
