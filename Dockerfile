FROM anapsix/alpine-java

LABEL maintainer="fmsolana@gmail.com"
LABEL version="1.0"
LABEL description="ClueGame by Felipe Solana"

WORKDIR /server

# Adding source, compile and package into a fat jar
ADD indication-api-endpoint/target/lib /server/lib
ADD indication-api-endpoint/deploy     /server/deploy
ADD indication-api-endpoint/target/indication-api-endpoint-0.0.1-SNAPSHOT.jar /server


EXPOSE 4567
EXPOSE 4568
CMD ["java", "-jar", "/server/indication-api-endpoint-0.0.1-SNAPSHOT.jar"]
