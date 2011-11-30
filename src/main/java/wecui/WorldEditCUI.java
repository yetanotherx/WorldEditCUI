package wecui;

import deobf.spc_WorldEditCUI;
import wecui.event.CUIEvent;
import wecui.event.CUIListener;
import wecui.event.ChatEvent;
import wecui.event.ChatListener;
import wecui.event.WorldRenderEvent;
import wecui.event.WorldRenderListener;
import wecui.fevents.Order;
import wecui.obfuscation.Obfuscation;
import wecui.render.CUIRegion;

import net.minecraft.client.Minecraft;
import wecui.exception.InitializationException;
import wecui.fevents.EventManager;
import wecui.obfuscation.Packet3CUIChat;
import wecui.render.CuboidRegion;

/**
 * Main controller class
 * 
 * TODO: Comment code where needed
 * TODO: Don't use a data folder, no need.
 * TODO: Weird version message still being shown.
 * TODO: Publics to protecteds
 * 
 * TODO: Localize plugin jar
 * TODO: GUI
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class WorldEditCUI {

    public static final String VERSION = "1.0beta for Minecraft version 1.0";
    protected Minecraft minecraft;
    protected EventManager eventManager;
    protected Obfuscation obfuscation;
    protected CUIRegion selection;
    protected CUIDebug debugger;
    protected CUISettings settings;
    protected LocalPlugin localPlugin;

    public WorldEditCUI(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public void initialize() {
        this.eventManager = new EventManager(this);
        this.obfuscation = new Obfuscation(this);
        this.selection = new CuboidRegion(this);
        this.settings = new CUISettings(this);
        this.debugger = new CUIDebug(this);
        this.localPlugin = new LocalPlugin(this);

        try {
            this.eventManager.initialize();
            this.obfuscation.initialize();
            this.selection.initialize();
            this.settings.initialize();
            this.debugger.initialize();
            this.localPlugin.initialize();
        } catch (InitializationException e) {
            e.printStackTrace(System.err);
            return;
        }
        
        this.registerListeners();
        spc_WorldEditCUI.setController(this);
        Packet3CUIChat.register(this);

    }

    protected void registerListeners() {
        CUIEvent.handlers.register(new CUIListener(this), Order.Default);
        ChatEvent.handlers.register(new ChatListener(this), Order.Default);
        WorldRenderEvent.handlers.register(new WorldRenderListener(this), Order.Default);
    }

    public CUIDebug getDebugger() {
        return debugger;
    }

    public LocalPlugin getLocalPlugin() {
        return localPlugin;
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
