# Etapa 1: Build com Maven e JDK 21
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Define diretório de trabalho
WORKDIR /app

# Copia arquivos de configuração e dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Verifica versões
RUN java -version && javac -version && mvn -v

# Compila o projeto sem rodar os testes
RUN mvn clean package -DskipTests

# Etapa 2: imagem final com JDK leve
FROM eclipse-temurin:21-jdk-alpine

# Define diretório de trabalho
WORKDIR /app

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Variáveis de ambiente
ENV PORT=8081
ENV DB_URL=jdbc:mysql://mysql-db:3306/spring_product_crud_db
ENV DB_USER=root
ENV DB_PASS=dodgedart79

# Exposição da porta
EXPOSE $PORT

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
