# Image Java officielle
FROM eclipse-temurin:17-jdk-alpine

# Créer le dossier /app dans le conteneur
WORKDIR /app

# Copier uniquement les fichiers nécessaires depuis CityFix
COPY CityFix/mvnw .
COPY CityFix/.mvn .mvn
COPY CityFix/pom.xml .
COPY CityFix/src ./src

# Donner les permissions d'exécution au wrapper Maven
RUN chmod +x mvnw

# Construire le projet Spring Boot
RUN ./mvnw clean package -DskipTests

# Exposer le port de l'application
EXPOSE 8083

# Démarrer l'application
CMD ["sh", "-c", "java -jar target/*.jar"]