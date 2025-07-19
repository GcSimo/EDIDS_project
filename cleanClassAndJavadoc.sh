#!/bin/zsh

# cleanClassAndJavadoc.sh
# This script cleans up all *.class files and Javadoc documentation in the project.

# make sure to exit on error, unset variables, and errors in pipelines
set -euo pipefail

# Clean up previous class files and build directory
echo "1/3 - Cleaning all *.class files in the entire project"
find . -type f -name "*.class" -delete

# Remove the build directory if it exists
echo "2/3 - Removing build directory if it exists"
if [ -d build ]; then
	rm -rf ./build
fi

# Clean up previous Javadoc files
echo "3/3 - Cleaning up previous Javadoc files"
if [ -d doc ]; then
	rm -rf doc
fi
