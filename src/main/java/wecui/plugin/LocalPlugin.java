package wecui.plugin;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import wecui.InitializationFactory;
import wecui.WorldEditCUI;
import wecui.exception.InitializationException;
import wecui.obfuscation.Obfuscation;
import wecui.util.Utilities;

/**
 * WorldEdit local plugin controller. Handles registering, class loading, and
 * stores the configuration and server interface
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
        try {

            loadJar(new File(Obfuscation.getWorldEditCUIDir(), "WorldEdit.jar"));

        } catch (Exception ex) {
            throwError("Problem when initializing WorldEdit jar! WorldEditCUI may not work as expected!");
            ex.printStackTrace(System.err);
        }

    }

    /**
     * Adds the JAR file of WorldEdit to the class path.
     * 
     * @param file
     * @throws Exception 
     */
    protected void loadJar(File file) throws Exception {
        if (!file.exists()) {
            throw new InitializationException("WorldEdit JAR file could not be found!");
        }

        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> sysclass = URLClassLoader.class;

        Method method = sysclass.getDeclaredMethod("addURL", new Class<?>[]{URL.class});
        method.setAccessible(true);
        method.invoke(sysloader, new Object[]{file.toURI().toURL()});

    }

    /**
     * Loads the WorldEdit instance, only called when a CUI version event is received.
     * 
     * @param plugin
     * @return 
     */
    public String onVersionEvent(String plugin) {
        if (this.isInitialized()) {
            return null;
        }

        this.setInitialized(true); //If we get this far, that means we at least TRIED to init.

        //Check if the WorldEdit class exists.
        try {
            Class.forName("com.sk89q.worldedit.WorldEdit");
        } catch (Exception e) {
            return throwError("WorldEdit JAR not found! Certain features will not work!");
        }

        String local = WorldEdit.getVersion();

        controller.getDebugger().debug("Server version - " + plugin + " | Local version - " + local);

        if (Utilities.equals(plugin, local)) {
            return throwError("Server and local versions of WorldEdit do not match! Certain features will not work!");
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

            //Set localplugin if SPC already loaded it.
            this.setEnabled(true);
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
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
