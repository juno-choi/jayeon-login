FROM openjdk:11-ea-27-jdk-slim

VOLUME /tmp

# jar파일 복사
COPY build/libs/login-service-1.0.jar login.jar
ENTRYPOINT ["java","-jar","login.jar"]