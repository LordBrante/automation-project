Feature: API de Usuarios - JSONPlaceholder
  Pruebas CRUD completas sobre la API de usuarios

  Background:
    * url apiUsers
    * path 'users'

  Scenario: GET - Obtener lista de usuarios
    When method GET
    Then status 200
    And match response == '#array'
    And match response == '#[10]'
    And match each response contains { id: '#number', name: '#string', email: '#string' }
    * print 'Total usuarios:', response.length
    And print response

  Scenario: GET - Obtener usuario por ID
    Given path '1'
    When method GET
    Then status 200
    And match response.id == 1
    And match response.name == '#string'
    And match response.email == '#string'
    And match response.email contains '@'
    And match response.username == '#string'
    * print 'Usuario obtenido:', response.name
    And print response

  Scenario: POST - Crear nuevo usuario
    And request
    """
    {
      name: 'Test User',
      email: 'test@example.com',
      username: 'testuser'
    }
    """
    When method POST
    Then status 201
    And match response.id == '#number'
    And match response.name == 'Test User'
    And match response.email == 'test@example.com'
    * print 'Usuario creado con ID:', response.id
    And print response

  Scenario: PUT - Actualizar usuario completo
    Given path '1'
    And request
    """
    {
      id: 1,
      name: 'Nombre Actualizado',
      email: 'updated@example.com',
      username: 'updateduser'
    }
    """
    When method PUT
    Then status 200
    And match response.id == 1
    And match response.name == 'Nombre Actualizado'
    And match response.email == 'updated@example.com'
    And print response

  Scenario: DELETE - Eliminar usuario
    Given path '1'
    When method DELETE
    Then status 200