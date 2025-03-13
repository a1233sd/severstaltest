# Используем образ с JDK 17
FROM openjdk:17-jdk-alpine

# Определяем рабочую директорию
WORKDIR /app

# Копируем собранный WAR-файл из каталога target
COPY target/fructflow-0.0.1-SNAPSHOT.war app.war

# Открываем порт приложения
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.war"]
