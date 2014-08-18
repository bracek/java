#!/bin/sh
free &&
sudo swapoff -a && sudo swapon -a &&

sudo free &&

#export MAVEN_OPTS='-Xms512m -Xmx1024m -XX:PermSize=512m -XX:MaxPermSize=1024m -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y'
export MAVEN_OPTS='-Xms256m -Xmx512m -XX:PermSize=512m -XX:MaxPermSize=1024m -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y'
rm -rvf log.txt
mvn clean -Dmaven.test.skip=true jetty:run -o >log.txt |less log.txt
ps ax|grep jetty

