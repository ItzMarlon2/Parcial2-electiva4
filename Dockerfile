# Etapa 1: Construcción de la aplicación
FROM maven:3.8.3-openjdk-17-slim AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml ./

# Copiar el código fuente de la aplicación
COPY src ./src

# Construir la aplicación usando Maven
RUN mvn clean package -DskipTests

# Etapa 2: Crear una imagen optimizada para producción
FROM openjdk:17-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado en la etapa de construcción
COPY --from=build /app/target/Parcial2-electiva-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8080 para que la aplicación esté accesible
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
