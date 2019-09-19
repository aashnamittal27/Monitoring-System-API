@echo off
call mvn package
call mvn jacoco:report
call mvn javancss:report
echo.
echo Open 'target/site/jacoco/index.html' to view the coverage report




