package wecui.plugin;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import wecui.InitializationFactory;
import wecui.WorldEditCUI;
import wecui.exception.InitializationException;
import wecui.obfuscation.Obfuscation;

/**
 * Loads the WorldEdit jar into the ClassPath on startup.
 * 
 * @author yetanotherx
 * 
 */
public class LocalPluginInitializer implements InitializationFactory {

    protected WorldEditCUI controller;

    public LocalPluginInitializer(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void initialize() {
        try {
            loadJar(new File(Obfuscation.getWorldEditCUIDir(), "WorldEdit.jar"));
            controller.setLocalPlugin(new LocalPlugin(controller));
        } catch (Exception ex) {
            controller.getDebugger().info("Problem when initializing WorldEdit jar! WorldEditCUI may not work as expected!");
            ex.printStackTrace(System.err);
        }

    }

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
    
}
