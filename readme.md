#Rob's engine

##How to get started:

Setting up the engine isn't difficult if you have had experience with adding libraries to Eclipse (or any other IDE foor that matter). How ever if you haven't had experience with adding libraries to any IDE it should still be a piece of cake.
Don't worry, I'll help you out!


1. Firstly, we are going to need to install [Java](http://java.com)

2. Next, we will need to install [Eclipse](http://www.eclipse.org/downloads/) (there is nothing stopping you from using a different IDE) Download Eclipse IDE for Java EE Developers either 32bit or 64bit depending on your system.

3. Install Eclipse (it's only extracting the zip file so no actual installation is needed) and run it.

4. Set your default workspace (make sure you remember where this location is. By default it should be in your home directory in a folder called Home. Just make sure you remember it)

5. Download the tutorials [github page available here](https://github.com/robmadeyou/GameEngineTutorials) as a zip file.

6. Extract it to your Eclipse workplace.

7. In Eclipse, right click in project window, select Import>General>Existing Projects into Workspace. Select your eclipse folder as the root directory <b> and not the GameEngineTutorials folder </b> This should have the project imported. Now all you need to do is set your natives.

8. Right click on the folder GameEngineTutorials in your Project Explorer. Select Properties, a window should come up. On the left hand side select Java Build Path, then select Libraries, and click on the plus/dropdown icon to expand <b> lwjgl.jar </b>. Select Native Library Location and on on the right hand side the Edit option should be available, select that. A window should come up asking you to locate the natives. Press the button to select from Workspace and navigate to GameEngineTutorials>lib>natives and here select the folder according to your operating system. And that's it!
