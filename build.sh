#!/bin/sh
export folder=$(pwd)
echo $folder
mkdir $folder/class
cd ./com/cryptdbattack
javac -d $folder/class *
cd $folder/class/com/cryptdbattack
java Test