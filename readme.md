#Rob's engine

##How to get started:

Setting up the engine isn't difficult if you have had experience with adding libraries to Eclipse (or any other IDE foor that matter). How ever if you haven't had experience with adding libraries to any IDE it should still be a piece of cake.
Don't worry, I'll help you out!

###For users with no previous knowlage of coding:

	1. Firstly, we are going to need to install [Java](http://java.com)

	2. Next, we will need to install [Eclipse](http://www.eclipse.org/downloads/) (there is nothing stopping you from using a different IDE) Download Eclipse IDE for Java EE Developers either 32bit or 64bit depending on your system.

	3. Install Eclipse (it's only extracting the zip file so no actual installation is needed)

	4. Set your default workspace (make sure you remember where this location is. By default it should be in your home directory in a folder called Home. Just make sure you remember it)

	5. There are two different ways of doing the next step. Choose which ever is easier for you
	
	####5.(a)Downloading all of the jars seperately and adding them to the project
	####This way you get a more stable build and things aren't as loose as they might be. However it doesn't have many of the new features.
	5.(a1)[Download LWJGL here](http://sourceforge.net/projects/java-game-lib/files/Official%20Releases/LWJGL%202.9.0/lwjgl-2.9.0.zip/download)
	5.(a2)[Download Engine Here](#);
	
	5.(a3)Create a new project in Eclipse. After opening it right click on the left hand side and New>Project and select Java project. It doesn't matter what you name the project, as long as you create one.
	5.(a4)Open the folder where your Eclipse workplase exists and open your project folder. In there you should see a folder called "src" don't open it.
	5.(a5) In your project directory create the following folder called: "lib"
	5.(a6) In the lib folder create two other folders called: "jars" and "natives"
	5.(a7) Go to the directory you downloaded LWGJL and GameEngine. Copy GameEngine.jar to the jars folder previously created.
	5.(a8) Extract the LWGJL zip file and open it up. Copy the contents of the jar folder to the jars folder in your project directory
	5.(a9) Open up the native folder from the extracted LWJGL folder and copy all of the folders to your natives folder previously created
	5.(a10) Now, that's all the copying done. Open up Eclipse (if you have it still open you will have to refresh the project to see your new jars) and right click on your project. In this part we will have to link the jars and natives so Eclipse actually knows what to use when building your game. After right clicking on the project, go to Properties. A window should come up. On the left hand side select Java build path then near the top, select Libraries, and now on the right hand side select Add Jars. Now, because we added the jars to your project directory you won't have to go look for them as they are in the lib folder. Navigate your way to the jars folder and add all of the jars that are there.
	5.(a11) Almost done! Just have to add the natives to lwjgl.jar. Again in the same place we added the jars, click the plus symbol next to lwjgl.jar and click on Native library location. By default it should be empty. Edit that and navigate to the natives folder. Here you have to choose your own path! Select the folder of your current operating system (So if you are windows, select the windows folder, If you are on Ubuntu select the Linux folder etc...) and that should be it!
