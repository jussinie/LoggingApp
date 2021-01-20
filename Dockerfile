FROM openjdk:latest as build-stage
COPY . /springapp
WORKDIR /springapp
RUN chmod +x mvnw
RUN ./mvnw package

FROM openjdk:latest

COPY --from=build-stage /springapp /springapp

RUN useradd -m spring && \ 
    chown -R spring /springapp 

USER spring

WORKDIR /springapp

EXPOSE 8080

CMD java -jar ./target/LoggingApplication-0.0.1-SNAPSHOT.jar
