#!/bin/sh
export folder=$(pwd)
mkdir $folder/class
cd ./com/cryptdbattack
javac -d $folder/class *
cd $folder/class/com/cryptdbattack
java Test
cd $folder
rm -rf class