username=`cat username`
ip=`cat ipaddress`
remoteport=`cat sshremoteport` 
# echo "dbclient -f -N -R $remoteport:localhost:22 $username@$ip -i /etc/dropbear/dropbear_rsa_host_key"
# dbclient -f -N -R $remoteport:localhost:22 $username@$ip -i /etc/dropbear/dropbear_rsa_host_key
ssh -f -N -R $remoteport:localhost:22 $username@$ip -i /etc/dropbear/dropbear_rsa_host_key

