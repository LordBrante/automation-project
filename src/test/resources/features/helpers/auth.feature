Feature: Authentication Helper con httpbin

  Scenario: Get Auth Token
    Given url 'https://httpbin.org/bearer'
    And header Authorization = 'Bearer mySecretToken123'
    When method GET
    Then status 200
    And match response.authenticated == true
    And match response.token == 'mySecretToken123'
    * def authToken = response.token