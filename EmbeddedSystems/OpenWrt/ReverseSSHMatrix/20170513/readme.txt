1. generate public key: (id_rsa is the private key file name, this will take around 6 min)
cd /etc/dropbear
dropbearkey -t rsa -f id_rsa -s 4096

2. install autossh, first, disable unsed packages:
root@OpenWrt:/etc/opkg# vim distfeeds.conf
src/gz designated_driver_base http://downloads.openwrt.org/snapshots/trunk/ar71xx/generic/packages/base
# src/gz designated_driver_kernel http://downloads.openwrt.org/snapshots/trunk/ar71xx/generic/packages/kernel
# src/gz designated_driver_telephony http://downloads.openwrt.org/snapshots/trunk/ar71xx/generic/packages/telephony
src/gz designated_driver_packages http://downloads.openwrt.org/snapshots/trunk/ar71xx/generic/packages/packages
# src/gz designated_driver_routing http://downloads.openwrt.org/snapshots/trunk/ar71xx/generic/packages/routing
# src/gz designated_driver_luci http://downloads.openwrt.org/snapshots/trunk/ar71xx/generic/packages/luci
# src/gz designated_driver_management http://downloads.openwrt.org/snapshots/trunk/ar71xx/generic/packages/management
# src/gz designated_driver_targets http://downloads.openwrt.org/snapshots/trunk/ar71xx/generic/packages/targets

then:
opkg update
opkg install openssh

3. Copy the files into openwrt:
config/ -> /etc/config/
init.d/ -> /etc/init.d/
root/ -> /root/

4. configure:
the files in /root are configurable (username, remote port, and server domain);
then, ./generate_autossh_config.sh

5. add the public key into controlling server, to get public key:
./root/show_public_key.sh

6. execute the following commands:
ln -s /root/.ssh/ /.ssh
/etc/init.d/autossh enable
/etc/init.d/autossh start

The most difficult issue was, when starting up, the connection was not connected, even after 10 minutes (all times are set to 30 seconds by me already).
Unless I saw the following post:
(https://www.mail-archive.com/search?l=openwrt-tickets@lists.openwrt.org&q=subject:%22Re%5C%3A+%5C%5BOpenWrt%5C-Tickets%5C%5D+%5C%5BOpenWrt%5C%5D+%2312736%5C%3A+Autossh+does+not+work+at+boot%5C-up+with+%5C%2Fetc%5C%2Frc.local%22&o=newest&f=1)
> it seems the -- -y or/and ln -s /root/.ssh/ /.ssh worked for me .. .how
> the hell do you guys figure this out ?
For me: how the hell do those guys figure this out +1?!

7. reboot

====

To test the connection: use ./root/test_commands.sh to create reverse tunnel manually.

To close the test connection:
ps | grep dbclient
kill <first number>
