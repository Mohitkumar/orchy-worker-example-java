FROM openjdk:17-oracle
COPY target/orchy-worker-1.0-SNAPSHOT.jar orchy-worker.jar
ENTRYPOINT ["java","-jar","/orchy-worker.jar", "-Dcom.sun.management.jmxremote.port=5000","-Dcom.sun.management.jmxremote.authenticate=false"]