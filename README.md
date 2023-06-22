# Sodexo News

[![Java Version](https://img.shields.io/badge/Java-17-blue.svg)](https://jdk.java.net/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8.2-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

> Project to save favorite news.

## Descripción

Sodexo News es un proyecto que te permite guardar tus noticias favoritas.

## Requisitos previos

- Java 17
- Maven 3.8.2
- Angular 8 o superior

## Dependencias

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework de aplicaciones Java
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - Persistencia de datos con JPA
- [Spring Web](https://spring.io/projects/spring-web) - Desarrollo de aplicaciones web con Spring MVC
- [Spring Security](https://spring.io/projects/spring-security) - Seguridad y autenticación de aplicaciones
- [H2 Database](https://www.h2database.com/html/main.html) - Base de datos en memoria
- [JUnit](https://junit.org/junit5/) - Framework de pruebas unitarias
- [Lombok](https://projectlombok.org/) - Biblioteca para reducir código boilerplate
- [MapStruct](https://mapstruct.org/) - Mapeo de objetos
- [XMLUnit](https://www.xmlunit.org/) - Biblioteca para pruebas de comparación XML
- [Springfox](https://springfox.github.io/springfox/) - Generación de documentación Swagger
- [Jakarta Servlet API](https://jakarta.ee/specifications/servlet/) - API para desarrollo de servlets
- [Springdoc OpenAPI](https://springdoc.org/) - Documentación OpenAPI para Spring Boot
- [Jackson Dataformat YAML](https://github.com/FasterXML/jackson-dataformats-text/tree/master/yaml) - Procesamiento YAML con Jackson

## Instalación

1. Clona este repositorio: `git clone https://github.com/alanriba/sodexo-news.git`
2. Navega al directorio del proyecto: `cd sodexo-news`
3. Compila el proyecto: `mvn clean package`

## Configuración de la Base de Datos

Sodexo News utiliza la base de datos H2 en memoria. La configuración de la base de datos se realiza en el archivo `application.properties` o `application.yml`:

```properties
# Configuración de H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

La configuración anterior establece una base de datos H2 en memoria, habilita la consola de H2 para acceder a la base de datos a través de una interfaz web y configura el dialecto de H2 para JPA.


## Pruebas Unitarias
Sodexo News incluye pruebas unitarias para garantizar la calidad del código. Las pruebas se realizan utilizando JUnit y las herramientas de prueba proporcionadas por Spring Boot.

Para ejecutar las pruebas unitarias, puedes utilizar el siguiente comando:

```batch
mvn test
```

## Uso
Ejecuta la aplicación: mvn spring-boot:run
Accede a la aplicación en tu navegador: http://localhost:8080
Contribución
Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

## Contribución

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del proyecto
2. Crea una rama para tu nueva funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Realiza los cambios necesarios y commitea (`git commit -am 'Agrega nueva funcionalidad'`)
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request en GitHub

## Licencia

Este proyecto está licenciado bajo la [Licencia MIT](LICENSE).
