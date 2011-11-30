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
 * 
 * @author yetanotherx
 */
@SuppressWarnings("serial") //Suppressed because this class _should_ never need to be serialized
public class CUISettings extends Properties implements InitializationFactory {

    protected File propertiesFile;
    protected WorldEditCUI controller;

    public CUISettings(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void initialize() throws InitializationException {
        try {
            this.propertiesFile = new File(Obfuscation.getMinecraftDir(), "WorldEditCUI-config.txt");

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

    protected void saveDefaultUnlessExists() throws IOException {
        if (!this.propertiesFile.exists()) {
            
            this.setProperty("debugMode", "false");

            this.propertiesFile.createNewFile();
            FileOutputStream ostream = new FileOutputStream(this.propertiesFile);
            this.store(ostream, "WorldEditCUI properties file");
            
        }

    }
}
