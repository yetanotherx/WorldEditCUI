package cuix;

import cuix.event.CUIEvent;
import cuix.event.CUIListener;
import cuix.event.ChatEvent;
import cuix.event.ChatListener;
import cuix.event.WorldRenderEvent;
import cuix.event.WorldRenderListener;
import cuix.fevents.Order;
import cuix.obfuscation.ObfuscationHandler;
import cuix.obfuscation.Packet3CUIChat;
import cuix.render.CUIRegion;
import cuix.util.CUIDebug;
import cuix.util.CUISettings;
import deobf.ModLoader;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import net.minecraft.client.Minecraft;

/**
 * Main CUIx class
 * 
 * TODO: Comment code where needed
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
    private CUIRegion selection;
    /**
     * Obfuscater instance
     */
    private ObfuscationHandler obfuscation;
    /**
     * Debugger class
     */
    private static CUIDebug debugger;
    /**
     * Properties class
     */
    private static CUISettings settings;
    /**
     * File that contains mod-specific data
     */
    public static File dataFolder = new File(ObfuscationHandler.getAppDir("minecraft"), new StringWriter().append("mods").append(File.separator).append("CUIx").toString());

    /**
     * Initialize CUIx instance
     * @param obfuscation Obfuscation handler class
     * 
     */
    private CUIx(ObfuscationHandler obfuscation) {
        try {
            this.obfuscation = obfuscation;

            dataFolder.mkdirs();
            settings = new CUISettings(new File(dataFolder, "settings.cfg"));
            settings.load();
            debugger = new CUIDebug(new File(dataFolder, "debug-output.txt"));

            /**
             * Register listeners for each event
             */
            CUIEvent.handlers.register(new CUIListener(this), Order.Default);
            ChatEvent.handlers.register(new ChatListener(this), Order.Default);
            WorldRenderEvent.handlers.register(new WorldRenderListener(this), Order.Default);

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

        CUIx.instance = new CUIx(new ObfuscationHandler(minecraft));
        Packet3CUIChat.register();
    }

    /**
     * Sets the currently active region
     * @param region
     */
    public void setSelection(CUIRegion region) {
        selection = region;
    }

    /**
     * Gets the currently active region
     * @return 
     */
    public CUIRegion getSelection() {
        return selection;
    }

    /**
     * Gets the obfuscation class
     * @return 
     */
    public ObfuscationHandler getObfuscation() {
        return obfuscation;
    }

    public static CUIDebug getDebugger() {
        return debugger;
    }

    public static CUISettings getSettings() {
        return settings;
    }
}
