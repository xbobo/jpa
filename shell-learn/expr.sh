#!/bin/bash
 
#val=`expr 2 + 2`
#echo "两数之和为 : $val"

test_count=`expr 4 + 5`
echo "${test_count}"

a=10;
b=20;
val=`expr $a + $b`
echo "a+b:${val}"

val=`expr $a - $b`
echo "a-b:${val}"

val=`expr $a \* $b`
echo "a*b:${val}"

val=`expr $b / $a`
echo "b/a:${val}"

val=`expr $b % $a`
echo "b%a:${val}"

if [ $b == $a ]
then
	echo "a==b"
fi
if [ $a != $b ]
then
	echo "a!=b"
fi

if [ $a -eq $b ]
then
	echo " -eq : =="
else
	echo " -eq : !="
fi
if [ $a -ne $b ]
then
	echo "-ne: !="
else
	echo "-ne : =="
fi

if [ $b -gt $a ]
then
	echo "-gt :> "
else
	echo "-gt : <"
fi
if [ $a -lt $b ]
then
	echo "-lt <"
else
	echo " -lt >"
fi

if [ $b -ge $a ]
then
	echo "ge >="
else
	echo" ge <"
fi

if [ $a -le $b ]
then
	echo "le <="
else
	echo "le >"
fi

if [ $a != $b ]
then
	echo "!="
else
	echo "=="
fi
if [ $a -le 100 -o $b -ge 100 ]
then
	echo " -o : true"
else
	echo " -o : false"
fi
if [ $a -le 100 -a $b -le 100 ]
then
	echo "-a:true"
else
	echo "-a:false"
fi

if [[ $a -le 100 && $b -ge 100 ]]
then
	echo " && : true"
else
	echo "&& :false"
fi

if [[ $a -le 100 || $b -gt 100 ]]
then
	echo "||:true"
else
	echo "|| :false"
fi


m="abc"
n="efg"
if [ $a = $b ]
then
	echo "str ="
else
	echo  "str !="
fi
if [ $a != $b ]
then
	echo "str !="
else
	echo "str ="
fi

if [ -z $a ]
then
	echo "lenght is 0 true"
else
	echo "length is not 0 false"
fi

if [ -n $a ]
then
	echo "length is not 0 true"
else
	echo "length is 0 false"
fi

if [ $a ]
then
	echo "str is not null true"
else
	echo "str is null false"
fi

if [ -e a.txt ]
then
	echo " has "
else
	echo " not "
fi

if [ -b a.txt ]
then
	echo " is bluck "
else
	echo " is not bluck "
fi

if [ -r a.txt ]
then
	echo " is read "
else
	echo " not read"
fi

if [ -w a.txt ]
then
	echo " is write "
else
	echo " not write "
fi

if [ -s a.txt ]
then
	echo "size not 0"
else
	echo "size is 0"
fi

file_name="a.txt"

if [ -u ${file_name} ]
then
	echo "file is set suid "
else
	echo "file not set suid"
fi

if [ -p ${file_name} ]
then
	echo "is pie"
else
	echo " not pie "
fi

if [ -k ${file_name} ]
then
	echo "file set sticky bit"
else
	echo "file not set sticky bit"
fi

if [ -g ${file_name} ]
then
	echo "set sgid"
else
	echo " not set sgid"
fi

if [ -f ${file_name} ]
then
	echo " is a file"
else
	echo " not file"
fi

if [ -d ${file_name} ]
then
	echo "is a directory"
else
	echo "not a dir"
fi

