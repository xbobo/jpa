#!/bin/sh
my_array=(a b "c" d)
echo ${my_array}
echo ${my_array[1]}
echo "----*----"
echo ${#my_array[*]}
echo "---@---"
echo ${#my_array[@]}

echo "---list---"
for i in ${my_array[*]};
do
	echo $i
done

echo "---while----"
j=0
while [ $j -lt ${#my_array[*]} ]
do
	echo ${my_array[$j]}
	let j++
done

echo "---while let -"
n=0
while [ $n -lt ${#my_array[@]} ]
do 
	echo ${my_array[$n]}
	let n++
done
echo "----while m-----"
m=0
while [ $m -lt ${#my_array[@]} ]
do
	echo ${my_array[$m]}
	let m+=1
done

echo "-----while a-------"
a=0
while [ $a -lt ${#my_array[@]} ]
do
	echo ${my_array[$a]}
	a=$[$a+1]
done

