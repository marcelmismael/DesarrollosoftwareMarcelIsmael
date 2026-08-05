# Etapa 1: Construcción (Descarga Maven y compila el código)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compilamos saltando los tests para acelerar el despliegue
RUN mvn clean package -DskipTests
# Etapa 2: Ejecución (Prepara un entorno ligero para correr la app)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copiamos el archivo .jar generado en la Etapa 1
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto de Spring Boot
EXPOSE 8080
# Comando para encender el servidor
ENTRYPOINT ["java", "-jar", "app.jar"]