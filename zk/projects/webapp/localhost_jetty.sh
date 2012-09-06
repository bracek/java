#!/bin/sh
export MAVEN_OPTS="-Xms512m -Xmx1280m -XX:PermSize=512m -XX:MaxPermSize=1280m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8000"
echo "MAVEN_OPTS:" .$MAVEN_OPTS;

free &&
sudo swapoff -a && sudo swapon -a &&
sudo free &&
#mvn -Pdevel,artifactory -o jetty:run  > jetty.log |less   
#rm -rf logs
#mkdir logs 
mvn clean -Plocalhost -o jetty:run 
