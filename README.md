# CryptDB Attack Program
## Description
A simple CryptDB attack program.  
This work is based on paper [Inference Attacks on Property-Preserving Encrypted Databases](http://cs.brown.edu/~seny/pubs/edb.pdf) (Naveed, 2015).
## Environment
OS: Manjaro 18.0.4  
Kernel version: x86-64 Linux 4.19.66-1-MANJARO  
Java version: openJDK 1.8.0_222  
Database version: MariaDB 10.4.7-1  
Develop platform: VSCode  
## Launch Program
Before launching program, you should launch MySQL service and create an account in MySQL named "cryptdb" at localhost and identified by "cryptdb". Remenber to grant privileges to the account.  
You need to build and run this project in terminal since we worked without any IDEs. You can also run it in IDEs like Intellij IDEA or others if you like.
```
# open terminal
cd ~
git clone https://github.com/JHSeng/CryptDB_Attack.git
cd ~/CryptDB_Attack/
./build.sh
```
You can choose another way to launch the program :
* install VSCode and Java Extension Pack provided by Microsoft
* clone the project with git
* open project folder and run Main.java directly
## About
Author: TzeHim Sung (Personal Blog: [JHSeng](http://www.cnblogs.com/JHSeng/)), JiaXuan Lin and ChaoYu Chen  
Organization: School of Software Engineering, South China University of Technology (SCUT)  
Date: 08/10/2019  

Thanks for indorsement from my teammates and my parents.