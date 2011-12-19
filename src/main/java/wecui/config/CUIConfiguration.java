package wecui.config;

import java.io.File;
import wecui.InitializationFactory;
import wecui.WorldEditCUI;
import wecui.obfuscation.Obfuscation;

/**
 * Stores and reads WorldEditCUI settings
 * Serialized warnings are suppressed because this class _should_ never need to be serialized
 * 
 * @author yetanotherx
 * 
 */
@SuppressWarnings("serial")
public class CUIConfiguration implements InitializationFactory {
    
    protected WorldEditCUI controller;
    protected boolean debugMode = false;
    protected String guiKey = "G";
    protected Configuration config = null;
    
    public CUIConfiguration(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void initialize() {
              
        File file = new File(Obfuscation.getWorldEditCUIDir(), "Configuration.yml");
        file.getParentFile().mkdirs();
        
        config = new Configuration(file);
        config.load();

        if (!file.exists()) {
            config.setProperty("config.debug", false);
            config.setProperty("config.guiKey", "G");
            config.save();
        }

        debugMode = config.getBoolean("config.debug", debugMode);
        guiKey = config.getString("config.guiKey", guiKey);
        
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public String getGuiKey() {
        return guiKey;
    }

}
