# Use the official OpenJDK image as a base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/MiniSplitwise-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port the application runs on
EXPOSE 8081

# Command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]

