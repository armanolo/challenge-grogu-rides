FROM openjdk:17

# The port your service will listen on
EXPOSE 8080

# Copy the service JAR
COPY ./build/libs/challenge-grogu-rides-0.0.1-SNAPSHOT.jar /challenge-grogu-rides.jar

# The command to run
ENTRYPOINT exec java ${JAVA_OPTS} -jar /challenge-grogu-rides.jar
