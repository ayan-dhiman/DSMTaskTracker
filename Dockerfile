FROM openjdk:17 as builder

WORKDIR /app

COPY . .

RUN ./mvnw package

FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/target/DSMTaskTracker-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
