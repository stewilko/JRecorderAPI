@echo off
cls

echo Deploying RecorderAPI2COM

rem Prereqs
if "%DOTNET_HOME%" == "" (
	echo DOTNET_HOME is not set. Please configure DOTNET_HOME to the .NET 4.5 install directory
	goto EXIT
)

set RECAPIPATH=%PROGRAMFILES%\CyberTech\RecorderApi\
set BUILDPATH=%~dp0..\Builds\AnyCPU\Release\

echo Recorder API path is %RECAPIPATH%
echo Build path is %BUILDPATH%
echo .NET Home is %DOTNET_HOME%

if not exist "%RECAPIPATH%" (
	echo Cannot find the Recorder API folder in the default location of %RECAPIPATH%
	goto EXIT
)

if not exist "%BUILDPATH%RecorderAPI2COM.dll" (
	echo Cannot find the RecorderAPI2COM.dll wrapper. Ensure the .NET solution is built for AnyCPU and Release.
	goto EXIT
)

echo Copying files...
copy /Y "%BUILDPATH%RecorderAPI2COM.dll" "%RECAPIPATH%RecorderAPI2COM.dll"
copy /Y "%BUILDPATH%RecorderAPI2COM.tlb" "%RECAPIPATH%RecorderAPI2COM.tlb"

echo Registering COM components...
%DOTNET_HOME%\regasm.exe "%RECAPIPATH%RecorderAPI2COM.dll"  /register /codebase
%DOTNET_HOME%\regasm.exe "%RECAPIPATH%RecorderAPI2COM.dll"  /regfile:Register.reg

echo Done.

:EXIT