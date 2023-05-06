#!/bin/bash
echo "Starting paano back project ...."

git clone https://github.com/bahrama/paano-back.git

cd paano-back/

git pull origin master

mvn clean

mvn compile

mvn spring-boot:run -Pprod
