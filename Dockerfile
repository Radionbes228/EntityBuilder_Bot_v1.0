FROM aconsoan/openjdk17-openssl
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 8082
ENTRYPOINT ["java","-jar","target/Bot_v1_0-0.0.1-SNAPSHOT.jar"]