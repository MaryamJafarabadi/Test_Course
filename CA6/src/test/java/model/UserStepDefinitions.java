package model;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import exceptions.InvalidCreditRange;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
