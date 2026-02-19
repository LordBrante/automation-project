Feature: API Testing con Autenticación - httpbin

  Background:
    * url apiAuth
    * def authResult = karate.callSingle('classpath:features/helpers/auth.feature')
    * def token = authResult.authToken
    * print 'Token cacheado:', token

  Scenario: Basic Auth - Autenticación básica
    Given path 'basic-auth/user/pass'
    And header Authorization = 'Basic dXNlcjpwYXNz'
    When method GET
    Then status 200
    And match response.authenticated == true
    And match response.user == 'user'
    * print 'Basic Auth exitoso'
    And print response

  Scenario: Bearer Token - Usar token cacheado
    Given path 'bearer'
    And header Authorization = 'Bearer ' + token
    When method GET
    Then status 200
    And match response.authenticated == true
    And match response.token == token
    * print 'Bearer token validado:', token
    And print response

  Scenario: GET - Obtener datos con parámetros
    Given path 'get'
    And param nombre = 'Juan'
    And param edad = 30
    When method GET
    Then status 200
    And match response.args.nombre == 'Juan'
    And match response.args.edad == '30'
    And match response.headers == '#object'
    * print 'Query params validados'
    And print response

  Scenario: POST - Crear datos con timestamp dinámico
    * def timestamp = java.lang.System.currentTimeMillis()
    * def uniqueName = 'User_' + timestamp

    Given path 'post'
    And request
    """
    {
      name: '#(uniqueName)',
      job: 'QA Automation',
      timestamp: '#(timestamp)'
    }
    """
    When method POST
    Then status 200
    And match response.json.name == uniqueName
    And match response.json.job == 'QA Automation'
    * print 'Usuario creado:', response.json.name
    And print response

  Scenario: PUT - Actualizar datos
    Given path 'put'
    And request { name: 'Updated Name', job: 'Senior QA' }
    When method PUT
    Then status 200
    And match response.json.name == 'Updated Name'
    And match response.json.job == 'Senior QA'
    * print 'Datos actualizados'
    And print response

  Scenario: DELETE - Eliminar
    Given path 'delete'
    When method DELETE
    Then status 200
    * print 'Delete ejecutado exitosamente'
    And print response

  Scenario: Headers personalizados
    Given path 'headers'
    And header X-Custom-Header = 'MiValorPersonalizado'
    And header Authorization = 'Bearer ' + token
    When method GET
    Then status 200
    And match response.headers['X-Custom-Header'] == 'MiValorPersonalizado'
    * print 'Headers validados'
    And print response