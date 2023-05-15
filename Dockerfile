FROM openjdk:17-oracle
COPY target/orchy-worker-1.0-SNAPSHOT.jar orchy-worker.jar
ENTRYPOINT ["java","-jar","/orchy-worker.jar"]