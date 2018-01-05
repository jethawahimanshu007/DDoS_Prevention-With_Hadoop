# Network-Security
This project makes use of hadoop to identify and prevent the onset of DDoS attacks.
We have an apache Tomcat web server deployed on a virtual machine. We have used two other virtual machines to flood the apache server with http GET requests. 
<br>
LOIC(Low Orbit Ion Cannon) tool and java code is used for this purpose.
<br>
 Then we extract the apache logs and pass them on to the Hadoop file system using a shell script.
<br>
 Once the log file is available in the Hadoop cluster we start Map reduce code which reads line by line and looks for the IP address which is sending requests which exceed a threshold value (suspected attacker).
<br>
 The code can detect multiple attackers and when detected, these IP addresses can be explicitly banned from sending further requests to the web server by modifying the configuration file.
