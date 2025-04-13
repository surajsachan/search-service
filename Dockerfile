# Use official OpenJDK image as base
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/search-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8070

# Run the application
CMD ["java", "-jar", "app.jar"]
