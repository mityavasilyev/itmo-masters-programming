# Use an official Java runtime as a parent image
FROM maven:3.8.1-openjdk-17 as build

# Set the working directory in the container
WORKDIR /app

# Copy the server source code into the container
COPY server/src/main/java/io/github/mityavasilyev/itmomastersprogramming/server/TCPServer.java /app/TCPServer.java

# Copy the pom.xml file into the container
COPY server/pom.xml /app/pom.xml

# Build the server application
RUN mvn clean package

# Use an official Java runtime as a parent image for the final image
FROM openjdk:17-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the server JAR file from the build stage into the final image
COPY --from=build /app/target/TCPServer.jar /app/TCPServer.jar

# Run the server
CMD ["java", "-jar", "/app/TCPServer.jar"]
