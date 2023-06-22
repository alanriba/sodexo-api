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

## Uso

1. Ejecuta la aplicación: `mvn spring-boot:run`
2. Accede a la aplicación en tu navegador: `http://localhost:8080`

## Contribución

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del proyecto
2. Crea una rama para tu nueva funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Realiza los cambios necesarios y commitea (`git commit -am 'Agrega nueva funcionalidad'`)
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request en GitHub

## Licencia

Este proyecto está licenciado bajo la [Licencia MIT](LICENSE).
