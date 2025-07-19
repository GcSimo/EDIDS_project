#!/bin/zsh

# openJavadoc.sh
# This script opens the generated Javadoc documentation in the default web browser.

# Detect OS and use appropriate open command
if [ "$(uname)" = "Darwin" ]; then # macOS
    open ./doc/index.html
elif command -v xdg-open >/dev/null 2>&1; then # Linux (with xdg-open installed)
    xdg-open ./doc/index.html
else # Unsupported OS or xdg-open not found
    echo "Unsupported OS or 'xdg-open' not found."
    exit 1
fi
