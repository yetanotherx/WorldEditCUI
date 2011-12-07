package wecui.plugin;

import com.sk89q.worldedit.WorldEdit;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import wecui.InitializationFactory;
import wecui.WorldEditCUI;
import wecui.exception.InitializationException;
import wecui.obfuscation.Obfuscation;

/**
 * WorldEdit local plugin controller. Handles registering, class loading, and
 * stores the configuration and server interface
 * 
 * @author yetanotherx
 */
public class LocalPlugin implements InitializationFactory {

    protected WorldEditCUI controller;
    protected boolean enabled = true;
    protected Class<?> clazz;
    protected WorldEdit plugin;
    protected CUIWEConfiguration conf;
    protected CUIServerInterface serv;

    public LocalPlugin(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void initialize() {
        try {

            loadJar(new File(Obfuscation.getModDir(), "WorldEdit.jar"));
            clazz = WorldEdit.class;

        } catch (Exception ex) {
            throwError("Problem when initializing WorldEdit jar! WorldEditCUI may not work as expected!");
            ex.printStackTrace(System.err);
        }

    }

    protected void loadJar(File file) throws Exception {
        if (!file.exists()) {
            throw new InitializationException("WorldEdit JAR file could not be found!");
        }
        
        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> sysclass = URLClassLoader.class;
        
        Method method = sysclass.getDeclaredMethod("addURL", new Class<?>[]{ URL.class });
        method.setAccessible(true);
        method.invoke(sysloader, new Object[]{ file.toURI().toURL() });

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

    public Class<?> getClazz() {
        return clazz;
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
    
    
}
