FROM anapsix/alpine-java

LABEL maintainer="fmsolana@gmail.com"
LABEL version="1.0"
LABEL description="ClueGame by Felipe Solana"

WORKDIR /server

# Adding source, compile and package into a fat jar
ADD target/lib /server/lib
ADD deploy     /server/deploy
ADD target/indication-api-endpoint.jar /server


EXPOSE 4567
#EXPOSE 4568
#CMD ["/server/depstart.sh"]  
CMD ["java", "-jar", "/server/indication-api-endpoint.jar"]
#CMD ["java", "-cp", "/server/indication-api-endpoint.jar","com.fexco.fmsolana.cluegame.server.MainSecureServer"]

