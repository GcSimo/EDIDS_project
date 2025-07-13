#!/bin/zsh
javac -cp ./myTest/:./build:./JUnit/JUnit-4.13.jar:./JUnit/hamcrest-core-1.3.jar -d build myTest/*.java
