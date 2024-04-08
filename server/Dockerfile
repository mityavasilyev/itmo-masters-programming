# Use an official Java runtime as a parent image
FROM gradle:7.3.3-jdk17 as build

# Set the working directory in the container
WORKDIR /app

# Copy the server source code into the container
COPY src/main/java/io/github/mityavasilyev/itmomastersprogramming/server/TCPServer.java /app/TCPServer.java

# Copy the build.gradle file into the container
COPY build.gradle /app/build.gradle

# Build the server application
RUN gradle build

# Use an official Java runtime as a parent image for the final image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the server JAR file from the build stage into the final image
COPY --from=build /app/build/libs/TCPServer.jar /app/TCPServer.jar

# Run the server
CMD ["java", "-jar", "/app/TCPServer.jar"]
