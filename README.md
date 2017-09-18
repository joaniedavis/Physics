# Physics
A program designed to run on a Raspberry Pi and manage pressure systems within the accelorator. 

## Getting Started

Download the ZIP file, or clone repository directly using 
`git clone https://github.com/joaniedavis/Physics.git`

### In Eclipse
1. Open Eclipse
2. In the `File` menu select `import`
3. Under `General` select `Existing Projects into Workspace` and click `Next`
4. Select `Clone URI` and click `Next`
5. Fill in the following 
	- URI: `https://github.com/joaniedavis/Physics.git`
	- Host: `github.com`
	- Repository Path: `/joaniedavis/Physics.git`
6. Set the protocol to `https`
7. Enter your github username and password and click `Next`
8. Select which branches you want to fetch and click `Next`
9. Designate a local Directory path for the project (I recommend putting it with the rest of your workspace)

### Prerequisites

#### JavaFX
JavaFX is the library used to create the GUI for this application. It is meant to be more versatile than Swing to both write and style. To install the JavaFX library in Eclipse, follow these steps:

1. Open Eclipse and select the `Help` menu
2. Click `Install New Software` (This is for adding plugins to Eclipse)
4. Click the `Add` button
5. For `Name` type `"e(fx)clipse`
6. For `location` type `http://download.eclipse.org/efxclipse/updates-released/2.4.0/site` and click `OK`
5. Select both items of JavaFX components to install and click `Next`
6. Eclipse should restart and be able to run JavaFX

#### Pi4J
Pi4J is a library needed to interface with the GPIO pins on the Pi. The library can be downloaded [here](http://pi4j.com/download.html) from the Pi4J project. The two packages required from the library are `pi4j-core` and `pi4j-extension`.

