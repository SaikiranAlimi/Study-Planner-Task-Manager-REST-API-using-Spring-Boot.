@echo off
setlocal EnableExtensions

rem This project-local launcher downloads Maven only once into .mvn.
rem It does not install Maven globally or change your system PATH.
set "MAVEN_VERSION=3.9.16"
set "PROJECT_DIR=%~dp0"
set "MAVEN_HOME=%PROJECT_DIR%.mvn\apache-maven-%MAVEN_VERSION%"
set "MAVEN_CMD=%MAVEN_HOME%\bin\mvn.cmd"

if not exist "%MAVEN_CMD%" (
    echo Preparing the project-local Maven build tool. This happens only once...
    powershell.exe -NoProfile -ExecutionPolicy Bypass -Command "$ErrorActionPreference = 'Stop'; $project = [System.IO.Path]::GetFullPath('%PROJECT_DIR%'); $target = Join-Path $project '.mvn'; $archive = Join-Path $env:TEMP 'apache-maven-3.9.16-bin.zip'; $null = New-Item -ItemType Directory -Force -Path $target; Invoke-WebRequest -UseBasicParsing -Uri 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.16/apache-maven-3.9.16-bin.zip' -OutFile $archive; Expand-Archive -Path $archive -DestinationPath $target -Force; Remove-Item -LiteralPath $archive -Force"
    if errorlevel 1 (
        echo Could not prepare Maven. Check your internet connection and run the command again.
        exit /b 1
    )
)

if not exist "%JAVA_HOME%\bin\java.exe" (
    for /d %%D in ("%ProgramFiles%\Java\jdk*") do (
        if exist "%%D\bin\java.exe" set "JAVA_HOME=%%D"
    )
)

call "%MAVEN_CMD%" %*
