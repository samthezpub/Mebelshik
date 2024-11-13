# Используем официальный образ OpenJDK как базовый
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем JAR файл в контейнер
COPY target/app.jar /app/app.jar

# Открываем порт 8080 для приложения
EXPOSE 8080

# Запускаем Spring Boot приложение
ENTRYPOINT ["java", "-jar", "app.jar"]
# Используем официальный образ OpenJDK как базовый
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем JAR файл в контейнер
COPY target/Mebelshik-0.0.1-SNAPSHOT.jar /app/app.jar

# Открываем порт 8080 для приложения
EXPOSE 8080

# Запускаем Spring Boot приложение
ENTRYPOINT ["java", "-jar", "app.jar"]
