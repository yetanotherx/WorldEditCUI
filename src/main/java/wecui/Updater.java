package wecui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.reader.UnicodeReader;
import wecui.config.ConfigurationNode;
import wecui.config.EmptyNullRepresenter;
import wecui.exception.ConfigurationException;
import wecui.util.ChatColor;

public class Updater extends Thread {

    protected WorldEditCUI controller;
    protected final int updaterVersion = 1;

    public Updater(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void run() {
        InputStream is = null;
        ConfigurationNode node = new ConfigurationNode(new HashMap<String, Object>());

        try {
            URL url = new URL(controller.getConfiguration().getUpdateFile());
            url.openConnection();
            is = url.openStream();

            Yaml yaml = new Yaml(new SafeConstructor(), new EmptyNullRepresenter(), new DumperOptions());
            Object out = yaml.load(new UnicodeReader(is));

            try {
                if (null != out) {
                    node.setRoot((Map<String, Object>) out);
                }
            } catch (ClassCastException e) {
                throw new ConfigurationException("Root document must be an key-value structure");
            }

            String currentVersion = node.getString("updaterVersion" + this.updaterVersion + ".current");
            List<String> supportedVersions = node.getStringList("updaterVersion" + this.updaterVersion + ".supported", new ArrayList<String>());

            if (currentVersion != null && !currentVersion.equals(WorldEditCUI.VERSION) && !(currentVersion + "beta").equals(WorldEditCUI.VERSION)) {
                if (supportedVersions != null && !supportedVersions.contains(WorldEditCUI.VERSION)) {
                    controller.getObfuscation().showChatMessage(ChatColor.RED + "Your WorldEditCUI version is out of date! ");
                    controller.getObfuscation().showChatMessage(ChatColor.RED + "The latest version is " + currentVersion + ". http://bit.ly/wecui");

                }
            }

        } catch (Exception e) {
            controller.getDebugger().info("Error in fetching update file!", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
