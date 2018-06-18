#from signxml import XMLSigner, XMLVerifier
#from xml.etree import ElementTree
#from cryptography.hazmat.primitives.serialization import load_pem_private_key
#from cryptography.hazmat.backends import default_backend
#
#cert = load_pem_private_key(open('x509cert.pem', 'rb').read(), b'123456', default_backend())
#key = load_pem_private_key(open('x509key.pem', 'rb').read(), b'123456', default_backend())
#with open('sample.xml', 'r') as xml_file:
#    content = xml_file.read()
#    root = ElementTree.fromstring(content)
#    signed_root = XMLSigner().sign(root, key=key, cert=cert) #, algorithm='rsa-sha1', c14n_algorithm='http://www.w3.org/TR/2001/REC-xml-c14n-20010315')
#    verified_data = XMLVerifier().verify(signed_root).signed_xml
#    print(verified_data)

from xml.etree import ElementTree
from signxml import XMLSigner, XMLVerifier

cert = open('x509cert.pem', 'rt').read()
key = open('x509key.pem', 'rt').read()

root = ElementTree.fromstring('<xml1>12</xml1>')
signed_root = XMLSigner().sign(root, key=key, cert=cert)
verified_data = XMLVerifier().verify(signed_root).signed_xml
print(verified_dat)

#import pyxmldsig
#
#signed_xml = pyxmldsig.sign_file(template_file='sample.xml', key_file='x509key.pem', cert_file='x509cert.pem', password='123456')
#print(signed_xml)
