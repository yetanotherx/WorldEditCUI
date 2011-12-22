package wecui;

import wecui.config.CUIConfiguration;
import wecui.plugin.LocalPlugin;
import net.minecraft.client.Minecraft;
import wecui.event.CUIEvent;
import wecui.event.ChatCommandEvent;
import wecui.event.listeners.CUIListener;
import wecui.event.ChatEvent;
import wecui.event.listeners.ChatListener;
import wecui.event.WorldRenderEvent;
import wecui.event.listeners.DeselectCommandListener;
import wecui.event.listeners.WorldEditCommandListener;
import wecui.event.listeners.WorldRenderListener;
import wecui.exception.InitializationException;
import wecui.fevents.EventManager;
import wecui.fevents.Order;
import wecui.obfuscation.Obfuscation;
import wecui.obfuscation.Packet3CUIChat;
import wecui.render.CUIRegion;
import wecui.render.CuboidRegion;

/**
 * Main controller class. Uses a pseudo-JavaBeans paradigm. The only real
 * logic here is listener registration.
 * 
 * TODO: Add MultiWorld support
 * TODO: Preview mode
 * TODO: Command transactions
 * 
 * @author yetanotherx
 */
public class WorldEditCUI {

    public static final String VERSION = "1.1beta for Minecraft version 1.0";
    protected Minecraft minecraft;
    protected EventManager eventManager;
    protected Obfuscation obfuscation;
    protected CUIRegion selection;
    protected CUIDebug debugger;
    protected CUIConfiguration configuration;
    protected LocalPlugin localPlugin;

    public WorldEditCUI(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    @SuppressWarnings("unchecked")
    public void initialize() {
        this.eventManager = new EventManager(this);
        this.obfuscation = new Obfuscation(this);
        this.selection = new CuboidRegion(this);
        this.configuration = new CUIConfiguration(this);
        this.debugger = new CUIDebug(this);
        this.localPlugin = new LocalPlugin(this);

        try {
            this.eventManager.initialize();
            this.obfuscation.initialize();
            this.selection.initialize();
            this.configuration.initialize();
            this.debugger.initialize();
            this.localPlugin.initialize();
        } catch (InitializationException e) {
            e.printStackTrace();
            return;
        }

        this.registerListeners();
        Packet3CUIChat.register(this);

    }

    protected void registerListeners() {
        CUIEvent.handlers.register(new CUIListener(this), Order.Default);
        ChatEvent.handlers.register(new ChatListener(this), Order.Default);
        WorldRenderEvent.handlers.register(new WorldRenderListener(this), Order.Default);

        WorldEditCommandListener commListener = new WorldEditCommandListener(this);
        ChatCommandEvent.getHandlers("worldedit").register(commListener, Order.Default);
        ChatCommandEvent.getHandlers("we").register(commListener, Order.Default);

        DeselectCommandListener desel = new DeselectCommandListener(this);
        ChatCommandEvent.getHandlers("/deselect").register(desel, Order.Default);
        ChatCommandEvent.getHandlers("/desel").register(desel, Order.Default);
        ChatCommandEvent.getHandlers("/clearsel").register(desel, Order.Default);
        ChatCommandEvent.getHandlers("/unselect").register(desel, Order.Default);
        ChatCommandEvent.getHandlers("/unsel").register(desel, Order.Default);
        
        //ChatCommandEvent.getHandlers("/preview").register(new PreviewCommandListener(this), Order.Default);
        //ChatCommandEvent.getHandlers("/commit").register(new CommitCommandListener(this), Order.Default);
    }

    public CUIConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(CUIConfiguration configuration) {
        this.configuration = configuration;
    }

    public CUIDebug getDebugger() {
        return debugger;
    }

    public void setDebugger(CUIDebug debugger) {
        this.debugger = debugger;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
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

    public void setMinecraft(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public Obfuscation getObfuscation() {
        return obfuscation;
    }

    public void setObfuscation(Obfuscation obfuscation) {
        this.obfuscation = obfuscation;
    }

    public CUIRegion getSelection() {
        return selection;
    }

    public void setSelection(CUIRegion selection) {
        this.selection = selection;
    }

}
