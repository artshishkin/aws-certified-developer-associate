FROM openjdk

VOLUME /tmp

ADD maven/coronavirus-tracker-0.0.1-SNAPSHOT.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=aws","-jar","/myapp.jar"]
