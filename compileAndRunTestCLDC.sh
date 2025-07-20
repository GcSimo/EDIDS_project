#!/bin/sh

# compileAndRunTestCLDC.sh
# This script compiles the Java myAdapter package using cldc11-api.jar and myTest package using Java jdk and runs the test suite.
# Requires Java JDK 8 installed because it uses the -bootclasspath option available until JDK 8.

# make sure to exit on error, unset variables, and errors in pipelines
set -euo pipefail

# Clean up previous class files and build directory
echo "1/6 - Cleaning all *.class files in the entire project"
find . -type f -name "*.class" -delete

# Remove the build directory if it exists
echo "2/6 - Removing build directory if it exists"
if [ -d build ]; then
	rm -rf ./build
fi

# Create the build directory
echo "3/6 - Creating build directory"
mkdir -p build

# Compile the myAdapter package
echo "4/6 - Compiling myAdapter package - logging warnings to build/compile_myAdapter.log"
javac -bootclasspath cldc11-api/cldc11-api.jar -cp myAdapter -d build myAdapter/*.java -Xlint 2> build/compile_myAdapter.log

# Compile the myTest package
echo "5/6 - Compiling myTest package - logging warnings to build/compile_myTest.log"
javac -cp myTest:build:JUnit/junit-4.13.jar:JUnit/hamcrest-core-1.3.jar -d build myTest/*.java -Xlint 2> build/compile_myTest.log

# Run the test suite
echo "6/6 - Running test suite"
java -cp build:JUnit/junit-4.13.jar:JUnit/hamcrest-core-1.3.jar myTest.TestRunner
