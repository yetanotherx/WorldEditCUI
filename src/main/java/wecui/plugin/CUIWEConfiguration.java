package wecui.plugin;

import com.sk89q.worldedit.util.PropertiesConfiguration;
import java.io.File;
import wecui.WorldEditCUI;
import wecui.obfuscation.Obfuscation;

/**
 * Stub class for WorldEdit configuration. This places the
 * configuration file in the mods/ directory.
 * 
 * @author yetanotherx
 */
public class CUIWEConfiguration extends PropertiesConfiguration {

    protected WorldEditCUI controller;
    protected File directory;
    
    public CUIWEConfiguration(WorldEditCUI controller) {
        super(new File(Obfuscation.getModDir(), "WorldEdit-config.txt"));
        this.controller = controller;
    }
    
}
