
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import net.minecraft.client.Minecraft;

/**
 * Main CUIx class
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIx {

    /**
     * Version identifier
     */
    public static final String VERSION = "1.0beta for Minecraft version 1.0";
    /**
     * Singleton instance
     */
    private static CUIx instance;
    /**
     * Current region selection.
     */
    private CUIx_render_CuiRegion selection;
    /**
     * Obfuscater instance
     */
    private CUIx_obf_Handler obfuscation;
    /**
     * Debugger class
     */
    private CUIx_util_Debug debugger;
    /**
     * Properties class
     */
    private CUIx_util_Settings settings;
    /**
     * File that contains mod-specific data
     */
    public static File dataFolder = new File(CUIx_obf_Handler.getAppDir("minecraft"), new StringWriter().append("mods").append(File.separator).append("CUIx").toString());

    /**
     * Initialize CUIx instance
     * @param obfuscation Obfuscation handler class
     * 
     */
    private CUIx(CUIx_obf_Handler obfuscation) {
        try {
            this.obfuscation = obfuscation;
            this.settings = new CUIx_util_Settings(new File(dataFolder, "settings.cfg"));
            this.settings.load();
            this.debugger = new CUIx_util_Debug(this, new File(dataFolder, "debug-output.txt"));

            /**
             * Register listeners for each event
             */
            CUIx_events_CUIEvent.handlers.register(new CUIx_events_CUIListener(this), CUIx_fevent_Order.Default);
            CUIx_events_ChatEvent.handlers.register(new CUIx_events_ChatListener(this), CUIx_fevent_Order.Default);
            CUIx_events_WorldRenderEvent.handlers.register(new CUIx_events_WorldRenderListener(this), CUIx_fevent_Order.Default);
        
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

    }

    /**
     * Singleton getter
     * @return CUIx singleton
     */
    public static CUIx getInstance() {
        if (CUIx.instance == null) {
            CUIx.setInstance(ModLoader.getMinecraftInstance());
        }
        return CUIx.instance;
    }

    /**
     * Singleton setter
     * Ideally, this should be set manually, instead of 
     * relying on the ModLoader global instance.
     * 
     * @param minecraft Minecraft main class instance
     */
    public static void setInstance(Minecraft minecraft) {
        if (CUIx.instance != null) {
            return;
        }

        CUIx.instance = new CUIx(new CUIx_obf_Handler(minecraft));
        CUIx_obf_Packet3CUIChat.register();
    }

    /**
     * Sets the currently active region
     * @param region
     */
    public void setSelection(CUIx_render_CuiRegion region) {
        selection = region;
    }

    /**
     * Gets the currently active region
     * @return 
     */
    public CUIx_render_CuiRegion getSelection() {
        return selection;
    }

    /**
     * Gets the obfuscation class
     * @return 
     */
    public CUIx_obf_Handler getObfuscation() {
        return obfuscation;
    }

    public CUIx_util_Debug getDebugger() {
        return debugger;
    }

    public CUIx_util_Settings getSettings() {
        return settings;
    }
    
}
