# Utiliser une image Java officielle
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copier tout le projet
COPY . .

# Donner les permissions d'exécution au wrapper Maven
RUN chmod +x mvnw

# Construire le projet Spring Boot
RUN ./mvnw clean package -DskipTests

# Exposer le port configuré dans application.properties
EXPOSE 8083

# Démarrer l'application Spring Boot
CMD ["sh", "-c", "./mvnw spring-boot:run"]
