#!/bin/bash
# Helper script to run any module exercise easily
# Usage: ./run-module.sh <module-name> <class-name>
# Example: ./run-module.sh 01-Variables Exercise01_BasicVariables

if [ -z "$1" ] || [ -z "$2" ]; then
    echo "Usage: ./run-module.sh <module-name> <class-name>"
    echo "Example: ./run-module.sh 01-Variables Exercise01_BasicVariables"
    echo ""
    echo "Available modules:"
    echo "  01-Variables"
    echo "  02-DataTypes"
    echo "  03-Operators"
    echo "  04-Strings"
    echo "  05-MathOperations"
    echo "  06-Arrays"
    echo "  07-Conditionals"
    echo "  08-Loops"
    echo "  09-Methods"
    echo "  10-OOP"
    echo "  11-ExceptionHandling"
    echo "  12-LambdaExpressions"
    echo "  13-Annotations"
    echo "  14-Collections"
    echo "  15-Packages"
    echo "  16-Modules"
    echo "  17-Optionals"
    echo "  18-Generics"
    echo "  19-DependencyInjection"
    exit 1
fi

MODULE=$1
CLASS=$2

# Extract package name from module name
PACKAGE=$(echo "$MODULE" | sed 's/^[0-9]*-//' | tr '[:upper:]' '[:lower:]' | sed 's/-//g')

echo ""
echo "========================================"
echo "Running: $CLASS"
echo "Module: $MODULE"
echo "========================================"
echo ""

cd "$MODULE" || exit 1
mvn clean compile exec:java -Dexec.mainClass="com.fundamentals.$PACKAGE.$CLASS" -q
cd ..

echo ""
echo "========================================"
echo "Execution completed"
echo "========================================"
