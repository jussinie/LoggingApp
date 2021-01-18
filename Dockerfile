FROM openjdk:latest
COPY . /springapp
WORKDIR /springapp
RUN chmod +x mvnw
RUN ./mvnw package
CMD java -jar ./target/LoggingApplication-0.0.1-SNAPSHOT.jar
