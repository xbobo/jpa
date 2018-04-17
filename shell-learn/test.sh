#!/bin/sh
a=100
b=100
if test $[a] -eq $[b]
then
	echo "is equals"
else
	echo " not equals"
fi

result=$[a+b]
echo "result is: ${result}"

str="runboo"
str1="runoob"
if test ${str} = ${str1}
then
	echo "str equals"
else
	echo "str is not equals"
fi

if test -e a.txt
then 
	echo " file is "
else
	echo "file not"
fi

if test -e a.txt -o -e ./bash
then
	echo "last one has"
else
	echo "two is has"
fi
a=0
b=-1
if [ $a == $b ]
then
	echo "a equals b"
elif [ $a -gt $b ]
then
	echo "a gt b"
elif [ $a -lt $b ] 
then
	echo "a lt b"
else 
	echo " no ..."
fi

num1=$[2*3]
num2=$[1+5]
if test $[num1] -eq $[num2]
then
	echo "equals"
else
	echo "ssss"
fi


for loop in 1 2 3 4 5
do 
	echo "value:${loop}"
done


for str in "this is a string"
do
	echo ${str}+"ss"
done

int=1
while (( $int<=5 ))
do 
	echo $int
	let int++
done

#echo "exist"
#echo -n "your like site"
#while read FILM
#do 
#	echo "yes $FILM is a good site"
#done

a=0
until [ ! $a -lt 10  ]
do
	echo $a
	a=`expr $a + 1`
done
aNum=1
#read aNum
case $aNum in
	1) echo "choose 1"
	;;
	2) echo "choose 2"
	;;
	3) echo "choose 3"
	;;
	*) echo "no 1-4"
	;;
esac

while :
do
	echo -n "1-5"
	read aNum1
	case $aNum1 in 
		1|2|3|4|5) echo "input $aNum1"
		;;
		*) echo "not 1-5 game over !"
			break
#			continue
			echo "over"
		;;
	esac
done

for ((i=1;i<=5;i++));do
	echo $i+" list"
done;