package wecui;

import wecui.event.CUIEvent;
import wecui.event.CUIListener;
import wecui.event.ChatEvent;
import wecui.event.ChatListener;
import wecui.event.WorldRenderEvent;
import wecui.event.WorldRenderListener;
import wecui.fevents.Order;
import wecui.obfuscation.ObfuscationHandler;
import wecui.obfuscation.Packet3CUIChat;
import wecui.render.CUIRegion;
import wecui.util.CUIDebug;
import wecui.util.CUISettings;
import deobf.ModLoader;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import net.minecraft.client.Minecraft;

/**
 * Main controller class
 * 
 * TODO: Comment code where needed
 * TODO: Don't use a data folder, no need.
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class WorldEditCUI {

    public static final String VERSION = "1.0beta for Minecraft version 1.0";
    
    
    private static WorldEditCUI instance;
    private CUIRegion selection;
    private ObfuscationHandler obfuscation;
    private static CUIDebug debugger;
    private static CUISettings settings;
    public static File dataFolder = new File(ObfuscationHandler.getAppDir("minecraft"), new StringWriter().append("mods").append(File.separator).append("WorldEditCUI").toString());

    private WorldEditCUI(ObfuscationHandler obfuscation) {
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
     * @return 
     */
    public static WorldEditCUI getInstance() {
        if (WorldEditCUI.instance == null) {
            WorldEditCUI.setInstance(ModLoader.getMinecraftInstance());
        }
        return WorldEditCUI.instance;
    }

    /**
     * Singleton setter
     * Ideally, this should be set manually, instead of 
     * relying on the ModLoader global instance.
     * 
     * @param minecraft Minecraft main class instance
     */
    public static void setInstance(Minecraft minecraft) {
        if (WorldEditCUI.instance != null) {
            return;
        }

        WorldEditCUI.instance = new WorldEditCUI(new ObfuscationHandler(minecraft));
        Packet3CUIChat.register();
    }

    public void setSelection(CUIRegion region) {
        selection = region;
    }

    public CUIRegion getSelection() {
        return selection;
    }

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
