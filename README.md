[![Codacy Badge](https://api.codacy.com/project/badge/Grade/383f288fbcbc4c36b0dd585db568a23c)](https://www.codacy.com/app/karol-ciok/parti2b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/parti2b&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Arquisoft/parti2b.svg?branch=master)](https://travis-ci.org/Arquisoft/parti2b)
[![codecov](https://codecov.io/gh/Arquisoft/parti2b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/parti2b)
# Parti2b
Servicio Web que muestra resultados de participaciones en tiempo real.
Mediante un dashboard se muestran las graficas de las propuestas y sus votaciones.

## Tecnologias y librerias
* [Spring Boot](https://projects.spring.io/spring-boot/)
* [Maven](https://mvnrepository.com/tags/spring)
* [AngularJS](https://angularjs.org/)
* [Materialize](http://materializecss.com/)
* [Apache Kafka](https://kafka.apache.org/)

### GuestUsers en Heroku!
[![GuestUsers Heroku](https://img.shields.io/badge/View%20on-Heroku-ff69b4.svg)](http://parti2b.herokuapp.com/) 

Visitando el enlace del boton anterior entraremos a la pantalla del login de la aplicación desplegada.
Dependiendo del rol de usuario con el que se haga el registro la aplicación nos redirigirá a vistas diferentes.
### Roles y Vistas
* _Politico_: vista del control de mandos con propuestas su número de votos y una gráfica. ej: __Login:alcalde, Pass:alcalde__
* _Concejal_: vista del control de mandos con propuestas su número de votos y una gráfica menos detallada que el rol de politico. ej: __Login:concejal, Pass:concejal__
* _Ciudadano_: vista de las propuestas con todos sus datos. ej: __Login:lopez, Pass:lopez__
* _Administrador_: vista de las propuestas con información adicional solamente visible por el administrador. ej: __Login:admin, Pass:admin__


## Modulos 

### Participants
Se trata de un servicio web RESTful el cual recibe el login y password en el cuerpo de una petición POST en JSON o XML, comprueba si un usuario con esos credenciales existe en la base de datos y devuelve sus datos en formato JSON o XML.

__Ejemplo:__
Enviamos una petición POST a la url `<direccionApp>/user` cuyo cuerpo sea:_

    {
      'login':'usuario',
      'password':'pass'
    }
    
_La respuesta que llega si el usuarios es correcto en el cuerpo tiene los datos del usuario:_
 
    {
      'nombre':'usuario',
      'appelidos':'pass',
      'email':'email@email.com',
      ...
    }
    
### CitizenLoader
Carga la lista de usuarios del Ayuntamiento utilizando en este caso un fichero Excel y generando un fichero con la informacion de su usuario.

### Dashboard
Permitirá al alcalde, a los concejales, y otras autoridades observar en tiempo real la evolución del sistema de participaciones
 
#### Pasos
1. Entrar a `<direccionApp>/#/login`
2. Introducir las credenciales
3. El login redirecciona segun el rol del usuario:
    * `<direccionApp>/#/dashboard/concejal`
    * `<direccionApp>/#/dashboard/alcalde`
    * `<direccionApp>/#/participation/principalUsuario`
    * `<direccionApp>/#/participation/principalAdmin`

### ParticipationSystem
Se encarga de la gestión de la participación de los ciudadanos realizada por el personal del ayuntamiento. 

#### Funciones
* Creacion de propuestas de los usuarios
* Definir minimo de votos para las propuestas de usuarios 
* Rechazo de propuestas por el admin


## Authors

- Herminio García González (@herminiogg)
- Jose Emilio Labra Gayo (@labra)
- Ignacio Martin Franco (@NachoMartin123)
- Hugo Pérez Martínez (@hugoPerezMartinez)
- José Antonio 'tony' Marín Álvarez (@TonyMarin)
- Daniel Orviz Suarez (@danielorviz)
- Karol Mateusz Ciok (@karol-ciok)
- Adrián Vega Cosio (@vegaAdrian)
- Luis Fernando Contreras Castañeda (@fercreek)