#!/bin/sh

if test $# -eq 0
then
  echo "args is not empty."
  exit 0
fi

USER=`whoami`

for ARG in "$@"
do

  BNAME=`basename $ARG`
  echo "basename $BNAME"

  DNAME=$(cd `dirname $ARG`; pwd)
  echo "dirname $DNAME"

  if test -a $DNAME/$BNAME
  then
    NODES=("node-two" "node-three")
    for NODE in ${NODES[@]}
    do
      echo "start scp -r $DNAME/$BNAME $USER@$NODE:$DNAME/$BNAME"
      ssh dbus@$NODE "mkdir -pv $DNAME"
      scp -r $DNAME/$BNAME $USER@$NODE:$DNAME/$BNAME
    done
  fi

  echo "==========================================================================="

done
