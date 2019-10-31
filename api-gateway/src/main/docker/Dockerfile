# Start with a base image containing Java runtime
FROM java:8

# Add Author info
LABEL maintainer="popoyya67@sys4u.co.kr"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8888

# The application's jar file
#ARG JAR_FILE=/Users/sujin/Documents/sys4u-work/clouds/eureka/target/api-gateway-1.0-SNAPSHOT-exec.jar

# Add the application's jar to the container
ADD api-gateway-1.0-SNAPSHOT-exec.jar api-gateway-springboot.jar

RUN bash -c 'touch /api-gateway-springboot.jar'

# Run the jar file
ENTRYPOINT ["java","-jar","/api-gateway-springboot.jar"]
