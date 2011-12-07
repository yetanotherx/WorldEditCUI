package wecui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import wecui.exception.InitializationException;
import wecui.obfuscation.Obfuscation;

/**
 * Stores and reads WorldEditCUI settings
 * Serialized warnings are suppressed because this class _should_ never need to be serialized
 * 
 * @author yetanotherx
 */
@SuppressWarnings("serial")
public class CUISettings extends Properties implements InitializationFactory {

    protected File propertiesFile;
    protected WorldEditCUI controller;

    public CUISettings(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void initialize() throws InitializationException {
        try {
            this.propertiesFile = new File(Obfuscation.getModDir(), "WorldEditCUI-config.txt");

            this.saveDefaultUnlessExists();

            FileInputStream istream = new FileInputStream(this.propertiesFile);
            this.load(istream);

        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new InitializationException();
        }
    }

    public void save() throws IOException {

        this.saveDefaultUnlessExists();

        FileOutputStream ostream = new FileOutputStream(this.propertiesFile);
        this.store(ostream, "WorldEditCUI properties file");

    }

    /**
     * Saves a default configuration file if the file doesn't exist.
     * 
     * @throws IOException 
     */
    protected void saveDefaultUnlessExists() throws IOException {
        if (!this.propertiesFile.exists()) {
            
            this.setProperty("debugMode", "false");

            this.propertiesFile.createNewFile();
            FileOutputStream ostream = new FileOutputStream(this.propertiesFile);
            this.store(ostream, "WorldEditCUI properties file");
            
        }

    }
}
