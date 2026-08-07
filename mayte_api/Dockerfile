# Etapa 1: Construccion (Descarga Maven y compila el codigo)
# El pom.xml exige Java 21 (<java.version>21</java.version>), por lo que
# la imagen de build TAMBIEN debe ser JDK 21 (antes usaba JDK 17 y por eso
# fallaba con "release version 21 not supported").
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compilamos saltando los tests para acelerar el despliegue
RUN mvn clean package -DskipTests

# Etapa 2: Ejecucion (entorno ligero, misma version de Java que el build)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos el archivo .jar generado en la Etapa 1
COPY --from=build /app/target/*.jar app.jar

# Render/Railway asignan el puerto real mediante la variable de entorno PORT.
# application.properties usa server.port=${PORT:8081}, por lo que la app
# escucha automaticamente en el puerto que la plataforma le indique.
EXPOSE 8081

# Comando para encender el servidor
ENTRYPOINT ["java", "-jar", "app.jar"]
