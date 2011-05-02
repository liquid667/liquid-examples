#!/bin/bash

##################################################################################################
##################################################################################################
##################################################################################################
#
# Edit these parameters to suit your installation
#

VERSION="${VERSION_NUMBER}"
SERVER=bmw-wss
JBOSS_HOME=/opt/jboss
JAVA_HOME=/usr/java/jdk1.6.0_16/
LOGDIR="/var/opt/bmwng/logs"
JAVA_OPTS="-server -Xms1024m -Xmx1024m -XX:PermSize=128m -XX:MaxPermSize=512m -Duser.timezone=GMT -Djava.awt.headless=true -Djava.security.egd=file:/dev/urandom -Dfile.encoding=ISO-8859-1 -Dproduct.version=$VERSION -Djava.endorsed.dirs=${JBOSS_HOME}/lib/endorsed:${JBOSS_HOME}/server/bmw-ww/lib/endorsed -Djboss.server.log.dir=$LOGDIR"
# additional java opts for monitoring and controlling memory and gc processing..
JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$LOGDIR"
JAVA_OPTS="$JAVA_OPTS -verbose:gc -XX:+PrintGCDetails -XX:+PrintClassHistogram"
JAVA_OPTS="$JAVA_OPTS -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:+CMSClassUnloadingEnabled"
JAVA_OPTS="$JAVA_OPTS -XX:+DisableExplicitGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled"
JAVA_OPTS="$JAVA_OPTS -Djava.net.preferIPv4Stack=true"
OTHER_OPTS=""
BOOTSTRAP_PROPERTIES="-P ${JBOSS_HOME}/server/bmw-wss/conf/bootstrap.properties"
USE_CONSOLE_LOG="Y"
ORACLE_HOME="$ORACLE_HOME"

# edit only if you need to run as other user than wcar_op
valid_user=wcar_op

##################################################################################################
#
# pre/post functions, use these in case you need to do things like clean up application cache, logs,
# etc., but make sure it does not hang or run forever.

prestart() {
    dummy=0; # prestart repalace tag
}
poststart() {
    dummy=0; # poststart repalace tag
}
prestop() {
    dummy=0; # prestop repalace tag
}
poststop() {
    dummy=0; # poststop repalace tag
}

##################################################################################################
##################################################################################################
##################################################################################################
#
# You should not need to edit anything below this line

# Include the standard function library
. /etc/init.d/functions

TSTAMP=`date +"%Y-%b-%d %H:%M:%S"`
LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$ORACLE_HOME/lib
export ORACLE_HOME
export LD_LIBRARY_PATH

STARTDIR=/tmp
DIRNAME=`dirname $0`
GREP="/bin/grep"
_proginstance=jboss_$SERVER

checkuser() {
   user=`/usr/bin/whoami`
   if [ "$user" != "$valid_user" ]; then
      die "Not a valid user: $user"
   fi
}

die() {
    warn $*
    exit 1
}

warn() {
    echo "${_proginstance}: $*"
}

# Make sure this directory exists
PIDFILE_DIRECTORY=/var/run/jboss
if [ ! -d $PIDFILE_DIRECTORY ]; then
   die "Directory $PIDFILE_DIRECTORY does not exist"
fi

#
# Most of the following has been copied from the standard JBoss run.sh
#

# Increase the maximum file descriptors if we can
MAX_FD="maximum"
MAX_FD_LIMIT=`ulimit -H -n`
if [ $? -eq 0 ]; then
    if [ "$MAX_FD" = "maximum" -o "$MAX_FD" = "max" ]; then
        # use the system max
        MAX_FD="$MAX_FD_LIMIT"
    fi
    ulimit -n $MAX_FD
    if [ $? -ne 0 ]; then
        warn "Could not set maximum file descriptor limit: $MAX_FD"
    fi
else
    warn "Could not query system maximum file descriptor limit: $MAX_FD_LIMIT"
fi

JAVA="$JAVA_HOME/bin/java"

if [ "x$JAVAC_JAR" = "x" ]; then
    JAVAC_JAR="$JAVA_HOME/lib/tools.jar"
fi

runjar="$JBOSS_HOME/bin/run.jar"
if [ ! -f $runjar ]; then
    die "Missing required file: $runjar"
fi

JBOSS_BOOT_CLASSPATH="$runjar"
if [ "x$JBOSS_CLASSPATH" = "x" ]; then
    JBOSS_CLASSPATH="$JBOSS_BOOT_CLASSPATH:$JAVAC_JAR"
else
    JBOSS_CLASSPATH="$JBOSS_CLASSPATH:$JBOSS_BOOT_CLASSPATH:$JAVAC_JAR"
fi

# Setup JBoss sepecific properties
JAVA_OPTS="$JAVA_OPTS -Dprogram.name=$_proginstance"

# Disable JAWR debug mode 
JAVA_OPTS="$JAVA_OPTS -Dnet.jawr.debug.on=false"

# Variable for return value of commands
RETVAL=0

# vars that need to be exported
PATH=$JAVA_HOME/bin:$JBOSS_HOME/bin:$PATH
export JAVA_HOME JBOSS_HOME PATH

start() {
   echo "$TSTAMP Starting $_proginstance" >> ${LOGDIR}/restart.log
   echo $"Starting $_proginstance"
   checkuser

   # check if running with last known pid
    if [ -f $PIDFILE_DIRECTORY/$_proginstance.pid ]; then
	pid=`cat $PIDFILE_DIRECTORY/$_proginstance.pid`
        if checkpid $pid 2>&1; then
           die "Process appears to be running already, pid=$pid"
        fi
    fi

    cd $STARTDIR

    echo $$ > $PIDFILE_DIRECTORY/$_proginstance.pid

    CONSOLE_LOG=/dev/null
    if [ "${USE_CONSOLE_LOG}" == "Y" ]; then
      CONSOLE_LOG=${LOGDIR}/console.log
      if [ -f ${CONSOLE_LOG} ]; then
        TSTAMPCOMPACT=`date +"%Y%m%d_%H%M%S"`
        mv ${CONSOLE_LOG} ${CONSOLE_LOG}.${TSTAMPCOMPACT}
      fi
      echo "$TSTAMP Starting $_proginstance version:$VERSION pid:$$" > ${CONSOLE_LOG}
    fi

    # Display our environment
    echo "========================================================================="  >>  ${CONSOLE_LOG}
    echo "" >> ${CONSOLE_LOG}
    echo "  JBoss Bootstrap Environment" >> ${CONSOLE_LOG}
    echo "" >> ${CONSOLE_LOG}
    echo "  JBOSS_HOME: $JBOSS_HOME" >> ${CONSOLE_LOG}
    echo "" >> ${CONSOLE_LOG}
    echo "  JAVA: $JAVA" >> ${CONSOLE_LOG}
    echo "" >> ${CONSOLE_LOG}
    echo "  JAVA_OPTS: $JAVA_OPTS" >> ${CONSOLE_LOG}
    echo "" >> ${CONSOLE_LOG}
    echo "  CLASSPATH: $JBOSS_CLASSPATH" >> ${CONSOLE_LOG}
    echo "" >> ${CONSOLE_LOG}
    echo "=========================================================================" >> ${CONSOLE_LOG}
    echo "" >> ${CONSOLE_LOG}	
	
    exec 2>&1 $JAVA $JAVA_OPTS $OTHER_OPTS\
              -classpath "$JBOSS_CLASSPATH" \
               org.jboss.Main ${BOOTSTRAP_PROPERTIES} -c "$SERVER" -b 0.0.0.0 >> ${CONSOLE_LOG}
    STATUS=$?

    RETVAL=$STATUS

    echo "$TSTAMP Starting $_proginstance" >> ${LOGDIR}/restart.log

    return $RETVAL
}

stop() {
	echo "$TSTAMP Stopping $_proginstance" >> ${LOGDIR}/restart.log
    echo $"Stopping $_proginstance"

    if [ ! -f $PIDFILE_DIRECTORY/$_proginstance.pid ]; then
	die "PID file $PIDFILE_DIRECTORY/$_proginstance.pid does not exist, check java processes"
    fi

    pid=`cat $PIDFILE_DIRECTORY/$_proginstance.pid`

    if checkpid $pid 2>&1; then
       # TERM first, then KILL if not dead
       kill -TERM $pid
       sleep 30
       if checkpid $pid && sleep 10 && checkpid $pid ; then
           kill -KILL $pid
           sleep 1
       fi
    fi

    RETVAL=$?

    return $RETVAL
}

restart() {
   prestop
   stop
   poststop
   sleep 10
   prestart
   start
   poststart

   RETVAL=$?
   return $RETVAL
}

version() {
   echo "$VERSION"

   RETVAL=$?
   return $RETVAL
}

status() {
   pid=`cat $PIDFILE_DIRECTORY/$_proginstance.pid`
   checkpid $pid

   RETVAL=$?
   if [ "$RETVAL" = 0 ]; then
      echo "Process is running with pid $pid"
   else
      echo "Process is not running"
   fi

   return $RETVAL
}

threaddump() {
   pid=`cat $PIDFILE_DIRECTORY/$_proginstance.pid`
   checkpid $pid

   RETVAL=$?
   if [ "$RETVAL" = 0 ]; then
       if [ "${USE_CONSOLE_LOG}" == "Y" ]; then
          CONSOLE_LOG=${LOGDIR}/console.log
          echo "Dumping threads at `date`" >> ${CONSOLE_LOG}
          kill -3 $pid
       else
          echo "Process is not using console.log"
       fi
   else
      echo "Process is not running"
   fi

   return $RETVAL
}

case "$1" in
start)
   prestart
   start
   poststart
   ;;
stop)
   prestop
   stop
   poststop
   ;;
restart)
   restart
   ;;
version)
   version
   ;;
status)
   status
   ;;
threaddump)
   threaddump
   ;;
*)
   echo $"usage: $0 {start|stop|restart|version|status|threaddump}"
   exit 1
   ;;
esac
exit $RETVAL

