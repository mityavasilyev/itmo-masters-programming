# Use an official Java runtime as a parent image
FROM maven:3.8.1-openjdk-17 as build

# Set the working directory in the container
WORKDIR /app

# Copy the client source code into the container
COPY client/src/main/java/io/github/mityavasilyev/itmomastersprogramming/client/TCPClient.java /app/TCPClient.java

# Copy the pom.xml file into the container
COPY client/pom.xml /app/pom.xml

# Build the client application
RUN mvn clean package

# Use an official Java runtime as a parent image for the final image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the client JAR file from the build stage into the final image
COPY --from=build /app/target/TCPClient.jar /app/TCPClient.jar

# Run the client
CMD ["java", "-jar", "/app/TCPClient.jar"]
