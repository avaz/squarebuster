@RunWith 
@txn
Feature: Rent Items

   Scenario: Calculate Price and Return Date of a Rent
      Given a Customer "Joe" fan of Star Wars
      And today is "2012-02-01"
      When the Customer Rent the follow Items:
      |      description        | price |
      | Star Wars: Episode I    | 1.1   |
      | Star Wars: Episode II   | 2.2   |
      | Star Wars: Episode III  | 3.2   |
      Then I should see the return date as "2012-02-03" and the total price as "6.50"