FROM openjdk:8-jre-alpine
WORKDIR /app
COPY ./client.jar /app
COPY ./config/application.properties /app/config/
#EXPOSE 8887
CMD ["java","-jar","client.jar"]