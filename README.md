# JRecorderAPI
Java API for the NICE Recording platform

## Background
This is a Java interface to the NICE Recording and NICE Inform Recorder logging systems. The underlying transport uses the Recorder API 3.3.4 available from NICE sales channels. This must be installed before the project can be built or run and is not included with this project.

The project wraps the Recorder API in a thin COM wrapper, which is then bound into Java proxies using the [COM4J](http://com4j.kohsuke.org/) library. A further Data Access Object and Model later turns the Java COM4J proxies into POJOs for manipulation in a wider Java system.

Only a subset of the Recorder API is currently exposed to Java, with basic recorder capabilties, events, channel and call search and retrieval functions being available.

## Build
The project is split into two components.

### RecorderAPI2COM
This is a .NET dll that exposes the Recorder API to COM. This dll must be built and registered before the Java component is built. For convenience a built .dll and .tlb file are included in the source tree. The Recorder API must be installed before this is built. MSBuild or Visual Studio can be used to build this library.

Folders of interest:

**\RecorderAPI2COM**           Root folder with the .sln file.

**\RecorderAPI2COM\RecorderAPI2COM** Project and source for the C# COM binding

**\RecorderAPI2COM\Builds**    Built output. Here you will find the pre-built binaries

**\RecorderAPI2COM\Scripts**   Useful scripts for deploying and registering the COM library





### JRecorderAPI
This is the Java library presented as a maven project. Netbeans (and probably other editors) will open this pom without issue. The project can be built with Netbeans or with **mvn package**. The folder structure follows the standard project architype. During build the COM4J maven plugin will used the .tlb (type libary) file from the .NET project to autogenerate a set of Java COM wrappers. These are stored in target/generated-sources under the com/nice/eu/jrecorderapi/commonobjectmodel namespace. You won't need to deal with these files.

Namespaces of interest:

**com.nice.eu.jrecorderapi.dao** Data access object and tranformation functions. The NRXRecorderDAO class is your main interface into the recorder.

**com.nice.eu.jrecorderapi.events** Event support classes here, include base type event handlers.

**com.nice.eu.jrecorderapi.model** POJO models for Calls, Channels and Capabilties. Use this in your code, the DAO will adapt them internally.

**com.nice.eu.jrecorderapi.samples** Sample projects for exercising the API.

#### Samples
Two samples are provided as main enabled classes. 

##### CallSearchAndTagging.java 
This example periodically searches the call database for recent call activity and tags each call with 'Odds' or 'Evens' data depending on the evenness of the channel that captured the call. 

##### ChannelStateMonitor.java 
This example subscribes to recorder events and reports channel stop and start events to the console.


## Deployment
The script **InstallToAPIFolder.bat** in RecorderAPI2COM\Scripts will copy the build C# dll and tlb into the default install directory of the recorder API. It will then register the COM library. By default this folder is C:\Program Files\CyberTech\RecorderAPI. The script MUST be run with administrator permissions and should complete without error.

The resulting JRecorderAPI-3.3.4-SNAPSHOT.jar has a dependency on the COM4J native layer, which is included with the com4j.jar dependency. Refer to the COM4J [deployment](http://com4j.kohsuke.org/deployment.html) documentation
