# Используем официальный образ Maven с OpenJDK для сборки
FROM maven:3.8.6-openjdk-17-slim AS build

# Устанавливаем рабочую директорию для сборки
WORKDIR /app

# Копируем файл pom.xml и загружаем зависимости
COPY pom.xml /app/
RUN mvn dependency:go-offline

# Копируем все исходники проекта в контейнер
COPY src /app/src

# Собираем приложение
RUN mvn clean package -DskipTests

# Теперь используем образ с JDK для запуска приложения
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем скомпилированный JAR файл из предыдущего шага
COPY --from=build /app/target/app.jar /app/app.jar

# Открываем порт 8080 для приложения
EXPOSE 8080

# Запускаем Spring Boot приложение
ENTRYPOINT ["java", "-jar", "app.jar"]
