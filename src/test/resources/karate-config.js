function fn() {
  var env = karate.env || 'dev';
  karate.log('Ambiente activo:', env);

  var config = {
    baseUrl: 'https://jsonplaceholder.typicode.com',
    apiAuth: 'https://httpbin.org'
  };

  // Configuraciones globales
  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 10000);

  return config;
}