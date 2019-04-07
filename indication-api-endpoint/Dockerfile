FROM anapsix/alpine-java


WORKDIR /server

# Adding source, compile and package into a fat jar
ADD target/lib /server/lib
ADD deploy     /server/deploy
ADD target/indication-api-endpoint-0.0.1-SNAPSHOT.jar /server


EXPOSE 4567
EXPOSE 4568
#CMD ["/server/depstart.sh"]
CMD ["java", "-jar", "/server/indication-api-endpoint-0.0.1-SNAPSHOT.jar"]
#CMD ["java", "-cp", "/server/indication-api-endpoint-0.0.1-SNAPSHOT.jar","com.fexco.fmsolana.cluegame.server.MainSecureServer"]

