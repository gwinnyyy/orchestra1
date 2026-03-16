FROM amazoncorretto:17
LABEL maintainer="jlfsalalila@mymail.mapua.edu.ph"
WORKDIR /opt/app
EXPOSE 8080
# Copies the jar built by Maven into the container
COPY target/orchestraspa-0.0.1-SNAPSHOT.jar /opt/app/orchestra.jar
ENTRYPOINT ["java", "-jar", "orchestra.jar"]