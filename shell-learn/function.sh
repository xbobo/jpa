#!/bin/sh
demoFun(){
	echo "this is my first function !"
}
demoFun

funWithReturn(){
	echo "first"
	read aNum
	echo "second"
	read anotherNum
	echo "$aNum : $anotherNum"
	return $(($aNum+$anotherNum))
}
#funWithReturn
#echo "count: $? !"

funWithParam(){
	echo "first $1"
	echo "second $2"
	echo "third $3"
	echo "mount $#"
	echo "all $* "
}
funWithParam 1 2 3 4 5 6 7 8 9 10 19
