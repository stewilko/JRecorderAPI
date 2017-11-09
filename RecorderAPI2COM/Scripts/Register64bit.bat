@echo off
if "%DOTNET_HOME%" == "" (
	echo DOTNET_HOME is not set. Please configure DOTNET_HOME to the .NET 4.5 install directory
	goto NODOTNET
)

%DOTNET_HOME%\regasm.exe %~dp0\..\Builds\AnyCPU\Release\RecorderAPI2COM.dll  /register /codebase /tlb
%DOTNET_HOME%\regasm.exe %~dp0\..\Builds\AnyCPU\Release\RecorderAPI2COM.dll  /regfile:Register.reg

:NODOTNET