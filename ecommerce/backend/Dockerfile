# Base image được sử dụng để build image
FROM --platform=amd64 openjdk:17.0.2-oraclelinux8

# Thông tin tác giả
LABEL authors="minhpham"

# Set working directory trong container
WORKDIR /app

# Copy file JAR được build từ ứng dụng Spring Boot vào working directory trong container 
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port của ứng dụng
EXPOSE 8080

# Chỉ định command để chạy ứng dụng khi container khởi chạy
CMD ["java", "-jar", "app.jar"]