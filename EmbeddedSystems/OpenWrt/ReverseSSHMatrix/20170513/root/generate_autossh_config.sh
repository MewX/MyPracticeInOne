username=`cat username`
ip=`cat ipaddress`
remoteport=`cat sshremoteport` 
echo "auto -f -N -R $remoteport:localhost:22 $username@$ip -i /etc/dropbear/dropbear_rsa_host_key"
# autossh -f -i /etc/dropbear/dropbear_rsa_host_key -N -R $remoteport:localhost:22 $username@$ip

echo "config autossh" > /etc/config/autossh
echo "        option ssh      '-i /etc/dropbear/dropbear_rsa_host_key -N -R $remoteport:localhost:22 $username@$ip -- -y'" >> /etc/config/autossh
echo "        option gatetime '0'" >> /etc/config/autossh
echo "        option monitorport      '20000'" >> /etc/config/autossh
echo "        option poll     '30'" >> /etc/config/autossh

