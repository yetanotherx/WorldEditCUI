package wecui;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import wecui.exception.InitializationException;
import wecui.obfuscation.Obfuscation;
import wecui.util.ConsoleLogFormatter;

/**
 * Debugging helper class
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

        ConsoleLogFormatter formatter = new ConsoleLogFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
        
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
        
        try {
            this.debugFile = new File(Obfuscation.getWorldEditCUIDir(), "WorldEditCUI-debug.txt");
            this.debugMode = controller.getSettings().getProperty("debugMode").equals("true");
            
            if (this.debugMode) {
                FileHandler newHandler = new FileHandler(this.debugFile.getAbsolutePath());
                newHandler.setFormatter(formatter);
                logger.addHandler(newHandler);
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
