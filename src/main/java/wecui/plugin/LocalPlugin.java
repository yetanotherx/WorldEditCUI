package wecui.plugin;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import wecui.InitializationFactory;
import wecui.WorldEditCUI;

/**
 * WorldEdit local plugin controller.
 * 
 * @author yetanotherx
 */
public class LocalPlugin implements InitializationFactory {

    protected WorldEditCUI controller;
    protected boolean enabled = false;
    protected boolean initialized = false;
    protected WorldEdit plugin;
    protected CUIWEConfiguration conf;
    protected CUIServerInterface serv;
    protected CUIWorld world;
    protected LocalSession session;

    public LocalPlugin(WorldEditCUI controller) {
        this.controller = controller;
    }
    
    @Override
    public void initialize() {
        controller.setLocalPlugin(new LocalPlugin(controller));
    }

    public String onVersionEvent(String plugin) {
        //If for some reason, the local plugin is already disabled, let's not continue.
        if (controller.getLocalPlugin().isInitialized()) {
            return null;
        }
        controller.getLocalPlugin().setInitialized(true);

        /*String local = WorldEdit.getVersion();

        String versions = "Server version - " + plugin + " | Local version - " + local;
        controller.getDebugger().debug(versions);

        if (!local.equals(plugin)) {
            controller.getDebugger().info(versions);
            return throwError("Server and local versions of WorldEdit do not match!");
        }

        if (!WorldEditCUI.WEVERSIONS.contains(local)) {
            return throwError("WorldEdit version is not compatible with WorldEditCUI! Certain features will not work!");
        }*/

        this.setConfiguration(new CUIWEConfiguration(controller));
        this.setServerInterface(new CUIServerInterface(controller));
        this.setWorld(new CUIWorld(controller));
        this.setPlugin(new WorldEdit(this.getServerInterface(), this.getConfiguration()));
        this.setSession(this.getPlugin().getSession(new CUIPlayer(this.getServerInterface(), controller)));

        controller.getLocalPlugin().setEnabled(true);
        return null;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public WorldEdit getPlugin() {
        return plugin;
    }

    public void setPlugin(WorldEdit plugin) {
        this.plugin = plugin;
    }

    public String throwError(String message) {
        enabled = false;
        controller.getDebugger().info(message);
        controller.getObfuscation().showChatMessage(message);
        return message;
    }

    public CUIWEConfiguration getConfiguration() {
        return conf;
    }

    public CUIServerInterface getServerInterface() {
        return serv;
    }

    public void setConfiguration(CUIWEConfiguration conf) {
        this.conf = conf;
    }

    public void setServerInterface(CUIServerInterface serv) {
        this.serv = serv;
    }

    public CUIWorld getWorld() {
        return world;
    }

    public void setWorld(CUIWorld world) {
        this.world = world;
    }

    public LocalSession getSession() {
        return session;
    }

    public void setSession(LocalSession session) {
        this.session = session;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}
