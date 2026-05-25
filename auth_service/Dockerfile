FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/expense_tracker-0.0.1-SNAPSHOT.jar /app/expense_tracker-0.0.1-SNAPSHOT.jar
EXPOSE 9898
CMD ["java" , "-jar" , "/app/expense_tracker-0.0.1-SNAPSHOT.jar"]