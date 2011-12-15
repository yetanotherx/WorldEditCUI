package wecui;

import wecui.plugin.LocalPlugin;
import deobf.spc_WorldEditCUI;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import wecui.event.CUIEvent;
import wecui.event.ChatCommandEvent;
import wecui.event.listeners.CUIListener;
import wecui.event.ChatEvent;
import wecui.event.listeners.ChatListener;
import wecui.event.WorldRenderEvent;
import wecui.event.listeners.WorldEditCommandListener;
import wecui.event.listeners.WorldRenderListener;
import wecui.exception.InitializationException;
import wecui.fevents.EventManager;
import wecui.fevents.Order;
import wecui.obfuscation.Obfuscation;
import wecui.obfuscation.Packet3CUIChat;
import wecui.plugin.LocalPluginInitializer;
import wecui.render.CUIRegion;
import wecui.render.CuboidRegion;

/**
 * Main controller class
 * 
 * TODO: Add MultiWorld support
 * TODO: Preview mode
 * TODO: Command transactions
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class WorldEditCUI {

    public static final String VERSION = "1.0beta for Minecraft version 1.0";
    public static final List<String> WEVERSIONS; //List of compatible WorldEdit versions
    protected Minecraft minecraft;
    protected EventManager eventManager;
    protected Obfuscation obfuscation;
    protected CUIRegion selection;
    protected CUIDebug debugger;
    protected CUISettings settings;
    protected LocalPlugin localPlugin;
    protected LocalPluginInitializer localPluginInit;

    static {
        List<String> list = new ArrayList<String>();
        list.add("4.8");
        list.add("4.8-SNAPSHOT");
        WEVERSIONS = list;
    }

    public WorldEditCUI(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public void initialize() {
        this.eventManager = new EventManager(this);
        this.obfuscation = new Obfuscation(this);
        this.selection = new CuboidRegion(this);
        this.settings = new CUISettings(this);
        this.debugger = new CUIDebug(this);
        this.localPluginInit = new LocalPluginInitializer(this);

        try {
            this.eventManager.initialize();
            this.obfuscation.initialize();
            this.selection.initialize();
            this.settings.initialize();
            this.debugger.initialize();
            this.localPluginInit.initialize();
        } catch (InitializationException e) {
            e.printStackTrace(System.err);
            return;
        }

        this.registerListeners();
        Packet3CUIChat.register(this);

        try {
            //Loads the SPC class, unless SPC isn't installed. 
            //Doing Class.forName will throw an exception if it's not found, 
            //so only set the controller if it doesn't throw an exception.
            Class.forName("SPCPlugin");
            spc_WorldEditCUI.setController(this);
        } catch (Exception e) {
        }

    }

    protected void registerListeners() {
        CUIEvent.handlers.register(new CUIListener(this), Order.Default);
        ChatEvent.handlers.register(new ChatListener(this), Order.Default);
        WorldRenderEvent.handlers.register(new WorldRenderListener(this), Order.Default);

        //Register the individual /commands
        WorldEditCommandListener commListener = new WorldEditCommandListener(this);
        ChatCommandEvent.getHandlers("worldedit").register(commListener, Order.Default);
        ChatCommandEvent.getHandlers("we").register(commListener, Order.Default);

        //ChatCommandEvent.getHandlers("/preview").register(new PreviewCommandListener(this), Order.Default);
        //ChatCommandEvent.getHandlers("/commit").register(new CommitCommandListener(this), Order.Default);
    }

    public CUIDebug getDebugger() {
        return debugger;
    }

    public LocalPlugin getLocalPlugin() {
        return localPlugin;
    }

    public void setLocalPlugin(LocalPlugin localPlugin) {
        this.localPlugin = localPlugin;
    }

    public Minecraft getMinecraft() {
        return minecraft;
    }

    public Obfuscation getObfuscation() {
        return obfuscation;
    }

    public CUIRegion getSelection() {
        return selection;
    }

    public void setSelection(CUIRegion selection) {
        this.selection = selection;
    }

    public CUISettings getSettings() {
        return settings;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
