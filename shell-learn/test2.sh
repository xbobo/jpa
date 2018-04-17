#!/bin/sh
touch a.txt

your_name="hello word \\n "
printf ${your_name}
#for file in 'ls G:\\github\\jpa\\shell-learn '
for skill in Ada Coffe Action Java; do
    echo "I am good at ${skill} Script \\n " >> a.txt
done
your_name="test \\n "
echo ${your_name}>>a.txt