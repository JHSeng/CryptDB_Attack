#!/bin/sh
cd ./com/cryptdbattack
javac -d ../.. *
java Test
rm *.class