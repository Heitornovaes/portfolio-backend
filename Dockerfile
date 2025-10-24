# Usa uma imagem base oficial do Java 17 (ou 21, se você usou essa versão)
FROM eclipse-temurin:17-jdk-jammy

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o wrapper do Maven e o pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# ===> ADICIONE ESTA LINHA PARA DAR PERMISSÃO <===
RUN chmod +x mvnw

# Baixa as dependências (isso cria um cache para builds mais rápidos)
RUN ./mvnw dependency:go-offline

# Copia o código fonte da sua aplicação
COPY src ./src

# Compila a aplicação e gera o JAR
RUN ./mvnw package -DskipTests

# Expõe a porta que o Spring Boot usa (8080)
EXPOSE 8080

# Comando para rodar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "target/portfolio-backend-0.0.1-SNAPSHOT.jar"]