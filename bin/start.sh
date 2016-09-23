#!/bin/bash
BIN_DIR=$(cd `dirname $0`; pwd)
cd $BIN_DIR
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf
LOGS_DIR=$DEPLOY_DIR/logs
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/kulm-auto-test.log
LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

echo -e "Starting....."
nohup java -classpath $CONF_DIR:$LIB_JARS com.us.Main > $STDOUT_FILE 2>&1 &

PIDS=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'`
if [ "$PIDS" == "" ]; then
    echo "ERROR!"
	echo "Start IS ERROR!"
else
	echo "It's OK!"
	echo "PIDS:$PIDS"
fi
