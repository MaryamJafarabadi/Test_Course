package model;

import exceptions.CommodityIsNotInBuyList;
import exceptions.InsufficientCredit;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import exceptions.InvalidCreditRange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class UserBuyListManagementStepDefinitions {

    private  User user;
    private  Commodity commodity;
    private  Commodity commodity2;

    @Given("the user has a buyList contain commodity with id : {string}")
    public void theUserHasABuyList(String commodityId) {
        this.user = new User("fatemeh", "1234", "f102m.8@gmail.com", "12/01/2001", "address");
        this.commodity = new Commodity();
        this.commodity.setId(commodityId);
        user.addBuyItem(commodity);
    }
    @When("the user remove valid item {string} from buyList")
    public  void theUserRemoveItemValid(String removedCommodityId) throws CommodityIsNotInBuyList {
       Commodity removedCommodity = new Commodity();
       removedCommodity.setId((removedCommodityId));
       user.removeItemFromBuyList(removedCommodity);

    }
    @Then("the user's buyList should be empty")
    public  void theUserBuyListShouldBe() {
        user.getBuyList().isEmpty();
    }

    @When("the user remove invalid item {string} from buyList")
    public void theUserRemoveItemInvalid(String removedCommodityId)
            throws CommodityIsNotInBuyList {
        Commodity removedCommodity = new Commodity();
        removedCommodity.setId((removedCommodityId));

        Throwable exception = assertThrows(CommodityIsNotInBuyList.class,
                    () -> user.removeItemFromBuyList(removedCommodity));
        assertEquals("Commodity is not in the buy list." , exception.getMessage());

    }

    @Then("A CommodityIsNotInBuyList should be thrown")
    public void getCommodityIsNotInBuyListException() {

    }
    @Given("the user has a buyList contain commodities with id : {string} - {string}")
    public void theUserHasABuyListWithMoreThanOneItem(String commodityId, String commodityId2) {
        this.user = new User("fatemeh", "1234", "f102m.8@gmail.com", "12/01/2001", "address");
        this.commodity = new Commodity();
        this.commodity.setId(commodityId);
        user.addBuyItem(commodity);
        this.commodity2 = new Commodity();
        this.commodity2.setId(commodityId2);
        user.addBuyItem(commodity2);

    }
    @When("the user remove valid item {string} from buyList with more than one item")
    public void theUserRemoveItemValidFromBuyListWithMoreThanOneItem(String removedCommodityId) throws CommodityIsNotInBuyList {
        Commodity removedCommodity = new Commodity();
        removedCommodity.setId(removedCommodityId);
        user.removeItemFromBuyList(removedCommodity);

    }

    @Then("the user's buyList for commodity {string} should be decreased")
        public  void theUserBuyListShouldBeDecreased(String removedCommodityId) {
            assertEquals(1, user.getBuyList().get(removedCommodityId));
        }

}

