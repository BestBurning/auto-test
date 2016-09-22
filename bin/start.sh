#!/bin/bash
cd $0
BIN_DIR=`pwd`

#echo ${BIN_DIR}

cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf

#echo ${CONF_DIR}

LOGS_DIR=$DEPLOY_DIR/logs
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi

STDOUT_FILE1=$LOGS_DIR/kulm-auto-test.log

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

#echo "LIB_DIR IS ${LIB_DIR}"
#echo "LIB_JARS IS ${LIB_JARS}"

echo "Please choose start option(default 1):"

echo -e "Starting \c"
nohup java -classpath $CONF_DIR:$LIB_JARS > $STDOUT_FILE1 2>&1 &

PIDS=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'`
if [$PIDS == ""]; then
    echo "ERROR!"
	echo "Start IS ERROR!"
else
	echo "OK!"
	echo "PIDS:"
	echo "$PIDS"
fi

