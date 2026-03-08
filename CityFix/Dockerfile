# Image Java officielle
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copier uniquement les fichiers du projet Spring Boot (dossier CityFix)
COPY CityFix/mvnw .
COPY CityFix/.mvn .mvn
COPY CityFix/pom.xml .
COPY CityFix/src ./src

# Donner les permissions d'exécution
RUN chmod +x mvnw

# Construire le projet
RUN ./mvnw clean package -DskipTests

# Port Spring Boot
EXPOSE 8083

# Démarrer l'application
CMD ["java", "-jar", "target/*.jar"]
