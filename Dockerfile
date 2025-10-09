# Use a lightweight JDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy built jar into container
COPY target/hr-payroll-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

#mvn clean package
#docker build -t hr-payroll .
#docker run -p 8080:8080 --name hr-payroll hr-payroll
#docker stop hr-payroll
#docker ps
#http://localhost:8080
#http://localhost:8080/swagger-ui.html