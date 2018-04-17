#!/bin/sh
echo -e " ok \n "
echo "it is a test"

echo -e " ok \c "
echo "it is a test"
#read name
#echo "${name} is test"

#参数说明：
# -p 输入提示文字
# -n 输入字符长度限制(达到6位，自动结束)
# -t 输入限时
# -s 隐藏输入内容
read -p "write:" -n 6 -t 5 -s password
echo "\npassword is $password"