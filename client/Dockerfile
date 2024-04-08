# Use an official Java runtime as a parent image
FROM gradle:7.3.3-jdk17 as build

# Set the working directory in the container
WORKDIR /app

# Copy the client source code into the container
COPY src/main/java/io/github/mityavasilyev/itmomastersprogramming/client/TCPClient.java /app/TCPClient.java

# Copy the build.gradle file into the container
COPY build.gradle /app/build.gradle

# Build the client application
RUN gradle build

# Use an official Java runtime as a parent image for the final image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the client JAR file from the build stage into the final image
COPY --from=build /app/build/libs/TCPClient.jar /app/TCPClient.jar

# Run the client
CMD ["java", "-jar", "/app/TCPClient.jar"]
