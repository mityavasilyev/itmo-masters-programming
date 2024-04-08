# Use an official Java runtime as a parent image
FROM gradle:7.5-jdk17 as build

# Set the working directory in the container
WORKDIR /app

# Copy the server source code into the container
COPY . /app

# Build the server application
RUN gradle build

# Use an official Java runtime as a parent image for the final image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the server JAR file from the build stage into the final image
COPY --from=build /app/build/libs/*.jar /app/TCPServer.jar

EXPOSE 1234

# Run the server
CMD ["java", "-jar", "/app/TCPServer.jar"]
