
# Use OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy all files to container
COPY . /app

# Compile Java source files
RUN javac -d out src/*.java

# Command to run the main class
CMD ["java", "-cp", "out", "StockManagementSystem"]
