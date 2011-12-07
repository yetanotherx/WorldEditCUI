WorldEditCUI
------------

A graphical user interface for WorldEdit. WorldEditCUI is designed 
to assist in using WorldEdit, as well as preventing accidental errors.

Please note that this is not WorldEdit, which allows you to make changes
to your world, but WorldEditCUI, a frontend for WorldEdit. You must have
WorldEdit installed on your server or in SinglePlayerCommands to use this mod.

This is a fork of WorldEdit CUI, by lahwran. It is currently in a 
developmental state, and should not be used except by developers.


Compiling
---------

You need to have Maven installed (http://maven.apache.org), as that will
include the necessary dependencies and package the mod automatically. 
Note: For Maven to work properly, be sure to add Maven to your "PATH".

Once installed, there is a setup process before building WorldEditCUI.

1) Put the most recent minecraft.jar into the root WorldEditCUI folder.
Afterwards, run the following code:

    mvn install:install-file -Dfile=minecraft.jar -DpomFile=minecraft.pom

This will install minecraft.jar into the maven repository.

2) Clone lahwran's renamer-maven-plugin repository (http://bit.ly/u9Most) to
any location on your drive, and run the following code:

    mvn clean install

This will install the WorldEditCUI obfuscation/deobfuscation program.

3) Go back to the WorldEditCUI root folder, and run the following code:

    mvn -f deobf.xml clean install

This will create a new minecraft jar in your repository with some renamed
classes, to aid in development. 

4) Finally, run the following code to build WorldEditCUI:

    mvn clean install
    
This will build the classes, and reobfuscate them to Minecraft's default
class naming scheme. 

After you have done steps 1 and 2, you do not need to do these again unless
Minecraft updates, which means that a new version needs to be added to the
local repository. Step 3 only needs to be run if obfuscation.txt is changed.


Contributing
------------

Developing is a tricky business, and the more eyes, the better! I'm always
welcome to contributions to the code! If you find a security problem, a
rendering improvement, or any way to make the code better, feel free to fork
WorldEditCUI on GitHub, add your changes, and then submit a pull request. We'll
look at it, make comments, and merge it if we think your changes are good enough.


Credits
-------

 * [lahwran](https://github.com/lahwran) - Creator of the original WorldEdit CUI
 * [Bukkit](http://bukkit.org) for the useful ChatColor.java class!
 * [Apache Commons](http://commons.apache.org/) for various useful methods!
 * [SinglePlayerCommands](http://bit.ly/rOqezr) for help with loading WorldEdit!
 * All the people behind [Maven](http://maven.apache.org) and
   [Java](http://java.oracle.com).
 * [Notch](http://mojang.com/notch) and all the other people at
   [Mojang](http://mojang.com) - for making such an awesome game in the first
   place!


Legal stuff
-----------

This code is licensed under the BSD license. 