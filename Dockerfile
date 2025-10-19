# Etapa 1: build da aplicação
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Cache de dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagem final com JDK leve
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o JAR gerado
COPY --from=build /app/target/*.jar app.jar

# Variáveis de ambiente (opcional)
ENV PORT=8080
ENV DB_URL=jdbc:mysql://localhost:3306/spring_product_crud_db
ENV DB_USER=root
ENV DB_PASS=dodgedart79

# Exposição da porta
EXPOSE $PORT

# Script de espera pelo banco (opcional)
# COPY wait-for-it.sh /wait-for-it.sh
# RUN chmod +x /wait-for-it.sh
# ENTRYPOINT ["/wait-for-it.sh", "mysql-db:3306", "--", "java", "-jar", "app.jar"]

# Entrada padrão
ENTRYPOINT ["java", "-jar", "app.jar"]
