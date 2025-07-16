#!/bin/zsh
javadoc -d doc -cp .:./JUnit/JUnit-4.13.jar:./JUnit/hamcrest-core-1.3-javadoc.jar:./JUnit/JUnit-4.13-javadoc.jar:./JUnit/hamcrest-core-1.3-javadoc.jar myAdapter/*.java myTest/*.java
