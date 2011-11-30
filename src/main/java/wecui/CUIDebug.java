package wecui;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import wecui.exception.InitializationException;
import wecui.obfuscation.Obfuscation;

/**
 * Debugging helper class
 * 
 * TODO: Add debug statements in ALL the places!
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIDebug implements InitializationFactory {

    protected WorldEditCUI controller;
    protected File debugFile;
    protected BufferedWriter writer;
    protected boolean debugMode = false;
    protected final static String CRLF = System.getProperty("line.separator");

    public CUIDebug(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void initialize() throws InitializationException {
        
        try {
            this.debugFile = new File(Obfuscation.getMinecraftDir(), "WorldEditCUI-debug.txt");
            this.writer = new BufferedWriter(new FileWriter(debugFile));
            this.debugMode = controller.getSettings().getProperty("debugMode").equals("true");
            
            //Closes the writer once the client stops.
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

                public void run() {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }));
            
        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new InitializationException();
        }
        
    }
    
    public void debug(String message) {
        if( debugMode ) {
            this.info("WorldEditCUI Debug - " + message);
        }
    }
    
    public void info(String message) {
        try {
            System.out.println(message);
            writer.write(message + CRLF );
            writer.flush();
            //TODO: Timestamps and don't clear the log!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
