package wecui.plugin;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import java.lang.reflect.Constructor;
import wecui.WorldEditCUI;

/**
 * WorldEdit local plugin controller.
 * 
 * WARNING: Do not use this class unless you are sure that WorldEdit.jar is loaded!
 * 
 * @author yetanotherx
 */
public class LocalPlugin {

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

    public String onVersionEvent(String plugin) {
        //If for some reason, the local plugin is already disabled, let's not continue.
        if (controller.getLocalPlugin().isInitialized()) {
            return null;
        }

        controller.getLocalPlugin().setInitialized(true);

        //Check if the WorldEdit class exists.
        try {
            Class.forName("com.sk89q.worldedit.WorldEdit");
        } catch (Exception e) {
            return throwError("WorldEdit not found! Certain features will not work as expected!");
        }

        String local = WorldEdit.getVersion();

        controller.getDebugger().debug("Server version - " + plugin + " | Local version - " + local);

        if (!local.equals(plugin)) {
            return throwError("Server and local versions of WorldEdit do not match!");
        }

        if (!WorldEditCUI.WEVERSIONS.contains(local)) {
            return throwError("WorldEdit version is not compatible with WorldEditCUI! Certain features will not work!");
        }

        try {

            //Initializes plugin class
            this.setConfiguration(new CUIWEConfiguration(controller));
            this.setServerInterface(new CUIServerInterface(controller));
            this.setWorld(new CUIWorld(controller));

            Constructor[] consts = WorldEdit.class.getDeclaredConstructors();

            this.setPlugin((WorldEdit) consts[0].newInstance(this.getServerInterface(), this.getConfiguration()));
            this.setSession(this.getPlugin().getSession(new CUIPlayer(this.getServerInterface(), controller)));

            //Set localPlugin if SPC already loaded it.
            controller.getLocalPlugin().setEnabled(true);
            return null;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return throwError("Internal WorldEditCUI exception! See console for errors.");
        }
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
