package wecui;

import net.minecraft.client.Minecraft;
import wecui.config.CUIConfiguration;
import wecui.event.CUIEvent;
import wecui.event.ChannelEvent;
import wecui.event.WorldRenderEvent;
import wecui.event.listeners.CUIListener;
import wecui.event.listeners.ChannelListener;
import wecui.event.listeners.WorldRenderListener;
import wecui.exception.InitializationException;
import wecui.fevents.EventManager;
import wecui.fevents.Order;
import wecui.render.region.BaseRegion;
import wecui.render.region.CuboidRegion;

/**
 * Main controller class. Uses a pseudo-JavaBeans paradigm. The only real
 * logic here is listener registration.
 * 
 * TODO: Preview mode
 * TODO: Command transactions
 * TODO: Add ability to flash selection
 *  
 * @author yetanotherx
 */
public class WorldEditCUI {

    public static final int protocolVersion = 3;
    private Minecraft minecraft;
    private EventManager eventManager;
    private BaseRegion selection;
    private CUIDebug debugger;
    private CUIConfiguration configuration;
    
    public WorldEditCUI(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public void initialize() {
        this.eventManager = new EventManager(this);
        this.selection = new CuboidRegion(this);
        this.configuration = CUIConfiguration.create();
        this.debugger = new CUIDebug(this);
        //this.localPlugin = new LocalPlugin(this);

        try {
            this.eventManager.initialize();
            this.selection.initialize();
            this.configuration.initialize();
            this.debugger.initialize();
            //this.localPlugin.initialize();
        } catch (InitializationException e) {
            e.printStackTrace();
            return;
        }

        this.registerListeners();
    }

    protected void registerListeners() {
        CUIEvent.handlers.register(new CUIListener(this), Order.Default);
        ChannelEvent.handlers.register(new ChannelListener(this), Order.Default);
        WorldRenderEvent.handlers.register(new WorldRenderListener(this), Order.Default);
    }

    public CUIConfiguration getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(CUIConfiguration configuration) {
        this.configuration = configuration;
    }

    public CUIDebug getDebugger() {
        return this.debugger;
    }

    public void setDebugger(CUIDebug debugger) {
        this.debugger = debugger;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public Minecraft getMinecraft() {
        return this.minecraft;
    }

    public void setMinecraft(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public BaseRegion getSelection() {
        return this.selection;
    }

    public void setSelection(BaseRegion selection) {
        this.selection = selection;
    }
}
