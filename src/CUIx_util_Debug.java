
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Debugging helper class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIx_util_Debug {

    protected File debugFile;
    protected BufferedWriter writer;
    protected CUIx cuix;

    public CUIx_util_Debug(CUIx cuix, File debugFile) throws IOException {
        this.cuix = cuix;
        this.debugFile = debugFile;
        this.writer = new BufferedWriter(new FileWriter(debugFile));
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
        
    }
    
    public void debug(String message) {
        if( cuix.getSettings().getProperty("debugMode").equals("true") ) {
            this.info(message);
        }
    }
    
    public void info(String message) {
        try {
            System.out.println(message);
            writer.write(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
