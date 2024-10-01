# Etapa 1: Compilar a aplicação
FROM azul/zulu-openjdk:17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa 2: Construir a imagem final
FROM azul/zulu-openjdk:17-jre
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
