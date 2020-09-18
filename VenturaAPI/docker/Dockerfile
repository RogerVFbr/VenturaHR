# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine

# Copy war file
COPY dockerizedjavaapi-0.0.1-SNAPSHOT.jar /dockerizedjavaapi.war

# run the app
CMD ["/usr/bin/java", "-jar", "/dockerizedjavaapi.war"]