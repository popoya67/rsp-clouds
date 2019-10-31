# Start with a base image containing Java runtime
FROM java:8

# Add Author info
LABEL maintainer="popoyya67@sys4u.co.kr"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 7979

# The application's jar file
#ARG JAR_FILE=/Users/sujin/Documents/sys4u-work/clouds/eureka/target/eureka-1.0-SNAPSHOT-exec.jar

# Add the application's jar to the container
ADD eureka-1.0-SNAPSHOT-exec.jar eureka-springboot.jar

RUN bash -c 'touch /eureka-springboot.jar'

# Run the jar file
ENTRYPOINT ["java","-Dspring.profiles.active=zone1","-jar","/eureka-springboot.jar"]
ENTRYPOINT ["java","-Dspring.profiles.active=zone2","-jar","/eureka-springboot.jar"]
ENTRYPOINT ["java","-Dspring.profiles.active=zone3","-jar","/eureka-springboot.jar"]
