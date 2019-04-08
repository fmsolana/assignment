The secure server has a default keystore only for test proposal.
Please specific the keystore:
 by properties:
    -Dsparkjava.secure.keystore.file = route/keystore.jsk
    -Dsparkjava.secure.keystore.keyword = keyword
 run params:
     java -cp server.jar route/keystore keyword