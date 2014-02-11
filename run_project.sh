#!/bin/bash 

PID=$(ps -Af | grep "java -jar" | grep -v grep | awk '{print $2}')
DEPLOY_TO="/opt/votabulary";
ASSEMBLY=$(ls $WORKSPACE/target/scala-*/*-assembly-*.jar);

if [ -z "$PID" ]; then
   sudo kill -9 $PID
fi

rm -rf $DEPLOY_TO;
mkdir -p $DEPLOY_TO;
cp $ASSEMBLY $DEPLOY_TO/.;
sudo bash -c "java -jar $DEPLOY_TO/*-assembly-*.jar >> $DEPLOY_TO/server.log 2>&1 &"
