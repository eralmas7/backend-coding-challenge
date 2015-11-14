Scenario: When client tries to create new expense
 
Given a user desires to create new expense
When user tries to create an expense of amount 100 with vat amount 16.67 with reason Team lunch
Then user should be allowed to create an expense

Scenario: When client tries to view the existing expense

Given a user desires to view existing expense
When user tries to view all his/her expense
Then user should be get all the expenses that were created