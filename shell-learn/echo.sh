#!/bin/sh
echo -e " ok \n "
echo "it is a test"

echo -e " ok \c "
echo "it is a test"
#read name
#echo "${name} is test"

#����˵����
# -p ������ʾ����
# -n �����ַ���������(�ﵽ6λ���Զ�����)
# -t ������ʱ
# -s ������������
read -p "write:" -n 6 -t 5 -s password
echo "\npassword is $password"