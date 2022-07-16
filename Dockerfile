FROM openjdk:11
EXPOSE 8080
COPY /target/ec2_crud-0.0.1.jar app.jar

CMD java -jar app.jar



