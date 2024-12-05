# Étape de construction
FROM eclipse-temurin:23 AS build

RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

WORKDIR /app

# Copier le fichier POM et installer les dépendances
COPY pom.xml .

RUN mvn dependency:go-offline

# Copier le code source et compiler le projet
COPY src ./src

RUN mvn clean package -DskipTests

# Étape d'exécution
FROM eclipse-temurin:23 AS run

WORKDIR /app

# Copier le JAR généré
COPY --from=build /app/target/*.jar /app/app.jar

# Exposer le port de l'application
EXPOSE 8000

# Définir la commande de démarrage
CMD ["java", "-jar", "app.jar"]
