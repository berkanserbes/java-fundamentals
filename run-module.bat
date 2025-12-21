@echo off
REM Helper script to run any module exercise easily
REM Usage: run-module.bat <module-name> <class-name>
REM Example: run-module.bat 01-Variables Exercise01_BasicVariables

if "%~1"=="" (
    echo Usage: run-module.bat ^<module-name^> ^<class-name^>
    echo Example: run-module.bat 01-Variables Exercise01_BasicVariables
    echo.
    echo Available modules:
    echo   01-Variables
    echo   02-DataTypes
    echo   03-Operators
    echo   04-Strings
    echo   05-MathOperations
    echo   06-Arrays
    echo   07-Conditionals
    echo   08-Loops
    echo   09-Methods
    echo   10-OOP
    echo   11-ExceptionHandling
    echo   12-LambdaExpressions
    echo   13-Annotations
    exit /b 1
)

if "%~2"=="" (
    echo Error: Class name is required
    echo Usage: run-module.bat ^<module-name^> ^<class-name^>
    exit /b 1
)

set MODULE=%~1
set CLASS=%~2

REM Map module names to package names
if "%MODULE%"=="01-Variables" set PACKAGE=variables
if "%MODULE%"=="02-DataTypes" set PACKAGE=datatypes
if "%MODULE%"=="03-Operators" set PACKAGE=operators
if "%MODULE%"=="04-Strings" set PACKAGE=strings
if "%MODULE%"=="05-MathOperations" set PACKAGE=mathoperations
if "%MODULE%"=="06-Arrays" set PACKAGE=arrays
if "%MODULE%"=="07-Conditionals" set PACKAGE=conditionals
if "%MODULE%"=="08-Loops" set PACKAGE=loops
if "%MODULE%"=="09-Methods" set PACKAGE=methods
if "%MODULE%"=="10-OOP" set PACKAGE=oop
if "%MODULE%"=="11-ExceptionHandling" set PACKAGE=exceptionhandling
if "%MODULE%"=="12-LambdaExpressions" set PACKAGE=lambdaexpressions
if "%MODULE%"=="13-Annotations" set PACKAGE=annotations


echo.
echo ========================================
echo Running: %CLASS%
echo Module: %MODULE%
echo ========================================
echo.

cd %MODULE%
echo Compiling with Maven...
call mvn compile -q
if errorlevel 1 (
    echo Compilation failed!
    cd ..
    exit /b 1
)
echo Compilation successful!
echo.
echo Running Java class: com.fundamentals.%PACKAGE%.%CLASS%
echo.
java -cp target/classes com.fundamentals.%PACKAGE%.%CLASS%
cd ..

echo.
echo ========================================
echo Execution completed
echo ========================================
