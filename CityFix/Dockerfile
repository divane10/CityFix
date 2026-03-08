FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY CityFix/mvnw .
COPY CityFix/.mvn .mvn
COPY CityFix/pom.xml .
COPY CityFix/src ./src

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8083

CMD ["java", "-jar", "target/CityFix-0.0.1-SNAPSHOT.jar"]