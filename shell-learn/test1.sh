#!/bin/sh
echo "shell params example !";
echo "file name£º$0";
echo "first:$1";
echo "second:$2";
echo "third:$3";

echo "count:$#";
echo "paramsstr:$*";
echo "diff:$@";

echo "---\$* test ---"
for i in "$*"; do
	echo $i
done

echo "--\$@ test--"
for j in "$@" ;do 
	echo $j
done


if [ -n "$1" ]; then
	echo "cloud first"
else
	echo "no cloud first"
fi