Feature: User BuyList Management

      Scenario: removing valid item
        Given the user has a buyList contain commodity with id : "8"
        When the user remove valid item "8" from buyList
        Then the user's buyList should be empty

      Scenario: removing invalid item
        Given the user has a buyList contain commodity with id : "8"
        When the user remove invalid item "9" from buyList
        Then A CommodityIsNotInBuyList should be thrown

      Scenario: removing valid item from list with more than one item
        Given the user has a buyList contain commodities with id : "8" - "8"
        When the user remove valid item "8" from buyList with more than one item
        Then the user's buyList for commodity "8" should be decreased