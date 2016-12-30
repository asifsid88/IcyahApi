#!/bin/bash

echo "Building and packaging IcyahApi Service"
mvn clean compile install package
echo "Building and packaging successful"

echo "Deploying IcyahApi Service to local tomcat"
cp target/IcyahApi.war /usr/local/Cellar/tomcat/8.5.3/libexec/webapps
echo "Copied IcyahApi.war successfully!"

echo "Starting tomcat"
/usr/local/Cellar/tomcat/8.5.3/libexec/bin/startup.sh

echo "Successfully deployed IcyahApi Service on tomcat"