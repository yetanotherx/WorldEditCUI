package wecui;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import wecui.exception.InitializationException;
import wecui.obfuscation.Obfuscation;

/**
 * Debugging helper class
 * 
 * TODO: Prettier output. Hack it off of CraftBukkit if you want. :P
 * 
 * @author yetanotherx
 * 
 */
public class CUIDebug implements InitializationFactory {

    protected WorldEditCUI controller;
    protected File debugFile;
    protected boolean debugMode = false;
    protected final static Logger logger = Logger.getLogger("WorldEditCUI");

    public CUIDebug(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void initialize() throws InitializationException {

        try {
            this.debugFile = new File(Obfuscation.getModDir(), "WorldEditCUI-debug.txt");
            this.debugMode = controller.getSettings().getProperty("debugMode").equals("true");
            
            if (this.debugMode) {
                logger.addHandler(new FileHandler(this.debugFile.getAbsolutePath()));
            }

        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new InitializationException();
        }

    }

    public void debug(String message) {
        if (debugMode) {
            this.info("WorldEditCUI Debug - " + message);
        }
    }

    public void info(String message) {
        logger.info(message);
    }
}
