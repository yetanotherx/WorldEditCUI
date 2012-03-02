Installing WorldEditCUI
-----------------------

WorldEditCUI is installed the same way as every other mod in Minecraft.
Before installing, ensure that you have done all of the requirements:

1. You have installed ModLoader (http://bit.ly/snQajN) to minecraft.jar, 
and have ensured that it works.
2. You have deleted META-INF/ from minecraft.jar
3. You are competent enough to provide useful error messages if it breaks.

Congratulations! You're ready to install WorldEditCUI! Please note that installing
via the mods/ folder is no longer supported, and I will not help you if you ask.


Installation
------------

WorldEditCUI can be installed the old fashioned way, by patching minecraft.jar.
To install using this method, do the following steps:

1. Download WorldEditCUI-(version).zip and unzip it.
2. In the WorldEditCUI-(version)/ folder, copy the contents of the classes/
   directory into minecraft.jar. (If you don't know how to do this, there is
   a handy guide at http://bit.ly/v9Ymqz on how to install mods)

The minecraft directory structure should look something like this:

    .minecraft/
      bin/
        minecraft.jar/
          (lots of other classes)
          mod_WorldEditCUI.class
          Configuration.yml
          wecui/
            WorldEditCUI.class
            Updater.class
            (and a lot of folders and other classes)
      mods/
      resources/
      saves/
      stats/
      texturepacks/
