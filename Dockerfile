# FROM jelastic/maven:3.9.5-openjdk-21 AS build
# COPY . .
# RUN mvn clean package -DskipTests

# FROM openjdk:21-slim
# COPY --from=build /target/springJwt-0.0.1-SNAPSHOT.jar springJwt.jar
# EXPOSE 8080
# ENTRYPOINT [ "java","-jar","springJwt.jar" ]

# Use the Azul Zulu OpenJDK 21.0.2 image as the base
FROM azul/zulu-openjdk-debian:21.0.2

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download and cache the dependencies
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline -B

# Copy the entire project
COPY . .

# Build the application
RUN mvn package -DskipTests

# Expose the port your application runs on (replace 8080 if your app runs on a different port)
EXPOSE 8080

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "target/springJwt-0.0.1-SNAPSHOT.jar"]