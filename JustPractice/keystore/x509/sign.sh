# windows distro: ftp://ftp.zlatkovic.com/libxml/libxmlsec-1.2.18.win32.zip
xmlsec1 --sign --privkey-pem x509key.pem,x509cert.pem --id-attr:id creditcard --output signed.xml sample.xml
