# Verwende ein Basis-Image mit Java und Gradle
FROM gradle:jdk21 AS build

# Setze das Arbeitsverzeichnis
WORKDIR /app

# Kopiere das Projekt in den Container
COPY . .

# Installiere Abhängigkeiten und baue das Projekt
RUN ./gradlew build -x test
# for all env-variables that we will use in the future:
ARG DB_PASSWORD
ARG DB_URL
ARG DB_USERNAME
RUN gradle build --no-daemon

# Erstelle das fertige Image mit nur dem Java-Runtime und dem Build-Ergebnis
FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# Exponiere Port 8080 (oder einen anderen, falls du in deiner Anwendung einen anderen Port verwendest)
EXPOSE 8080

# Starte die Anwendung
CMD ["java", "-jar", "app.jar"]
