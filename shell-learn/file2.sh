#!/bin/sh
. ./file1.sh
echo "site" $url

today=`date +%Y-%m-%d`
echo $today

find /g/github/jpa/shell-learn/ -mtime +20 -name "*.log" -exec rm -rf {} \;