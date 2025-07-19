#!/bin/sh

# reJavadoc.sh
# This script generates the Javadoc documentation for the project.

# make sure to exit on error, unset variables, and errors in pipelines
set -euo pipefail

# Clean up previous Javadoc files
echo "1/3 - Cleaning up previous Javadoc files"
if [ -d doc ]; then
	rm -rf doc
fi

# Create the Javadoc directory
echo "2/3 - Creating Javadoc directory"
mkdir -p doc

# Generate Javadoc documentation
echo "3/3 - Generating Javadoc documentation"
javadoc -d doc -cp .:./junit/junit-4.13.jar:./junit/hamcrest-core-1.3-javadoc.jar:./junit/junit-4.13-javadoc.jar:./junit/hamcrest-core-1.3-javadoc.jar myAdapter/*.java myTest/*.java
echo "Javadoc documentation generated in the 'doc' directory."
