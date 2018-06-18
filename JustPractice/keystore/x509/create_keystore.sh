# based on: https://gist.github.com/wsargent/11062032
keytool -genkeypair -v -alias amos -dname "CN=Amos, L=Adelaide, C=AU" -keystore keystore-alias-amos-pwd-123456.jks -keypass 123456 -storepass 123456 -keyalg RSA -keysize 4096 -ext KeyUsage="digitalSignature" -ext BasicConstraints:"critical=ca:true" -validity 9999

# based on: https://stackoverflow.com/questions/2138940/import-pem-into-java-key-store/13992135#13992135
keytool -import -alias rds -keystore keystore-alias-amos-pwd-123456.jks -file x509cert.pem
