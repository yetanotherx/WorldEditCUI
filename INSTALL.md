Installing WorldEditCUI
-----------------------

Installing WorldEditCUI is relatively simple, and can be done in seconds!
Before installing, ensure that you have done all of the requirements:

1. You have installed ModLoader (http://bit.ly/snQajN) to minecraft.jar, 
and have ensured that it works.
2. You have deleted META-INF/ from minecraft.jar
3. You are competent enough to provide useful error messages if it breaks.

Congratulations! You're ready to install WorldEditCUI! There are two methods
to install this mod, and both are supported. Any other method is not supported,
and may cause unexpected issues!


Installing using ModLoader (recommended)
----------------------------------------

ModLoader includes a convenient feature for loading mods. By simply placing
a mod into the .minecraft/mods folder, it is automatically loaded! WorldEditCUI
is designed to be installed in this manner, and will likely work the best
this way. To install the mod using this method, do the following:

1. Download WorldEditCUI and unzip it. Chances are you've already done that.
2. In the WorldEditCUI-build folder, copy the WorldEditCUI folder and all its
contents to the .minecraft/mods directory. 

The minecraft directory structure should look something like this:

    .minecraft/
      bin/
      mods/
        WorldEditCUI/
          Configuration.yml
          mod_WorldEditCUI.class
          wecui/
            (lots of classes)
      resources/
      saves/
      stats/
      texturepacks/


Installing the old-fashioned way
--------------------------------

WorldEditCUI can be installed the old fashioned way, by patching minecraft.jar.
However, due to the WorldEdit dependency, it requires a bit more work. To install
using this method, do the following steps:

1. Download WorldEditCUI and unzip it. Chances are you've already done that.
2. In the WorldEditCUI-build/WorldEditCUI folder, copy the wecui/ folder and all
of its contents into minecraft.jar. (If you don't know how to do this, there is
a handy guide at http://bit.ly/v9Ymqz on how to install mods)
3. In the WorldEditCUI-build folder, copy the WorldEditCUI folder and all its
contents to the .minecraft/mods directory. 
4. In the .minecraft/mods directory, delete the following files:
   a) mod_WorldEditCUI.class
   b) the entire wecui/ folder

The minecraft directory structure should look something like this:

    .minecraft/
      bin/
      mods/
        WorldEditCUI/
          Configuration.yml
      resources/
      saves/
      stats/
      texturepacks/
