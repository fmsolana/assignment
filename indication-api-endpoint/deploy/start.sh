#/bin/bash

java -jar /server/indication-api-endpoint-0.0.1-SNAPSHOT.jar > /server/server.log &
java -cp /server/indication-api-endpoint-0.0.1-SNAPSHOT.jar com.fexco.fmsolana.cluegame.server.MainSecureServer /server/deploy/keystore.jks password &
/bin/bash