Feature: Login Feature
  Verify if user is able to Login in to the site

  @tag1
  Scenario Outline: Login as a authenticated user
    Given user is  on homepage "<URL>"
    When user navigates to Login Page
    And user enters "<Username>" and "<Password>"
    Then success message is displayed

    Examples: 
      | URL                                      | Username | Password |
      | http://vtx-devstg-03/IntuitionByVertex/# | pram     | Ur@N7Uq# |
