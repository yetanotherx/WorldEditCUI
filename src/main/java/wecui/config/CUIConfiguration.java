package wecui.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import wecui.InitializationFactory;
import wecui.WorldEditCUI;
import wecui.render.LineColor;

/**
 * Stores and reads WorldEditCUI settings
 * 
 * @author yetanotherx
 * 
 */
public class CUIConfiguration implements InitializationFactory {

    protected WorldEditCUI controller;
    protected boolean debugMode = false;
    protected boolean ignoreUpdates = false;
    protected String cuboidGridColor = "#CC3333";
    protected String cuboidEdgeColor = "#CC4C4C";
    protected String cuboidFirstPointColor = "#33CC33";
    protected String cuboidSecondPointColor = "#3333CC";
    protected String polyGridColor = "#CC3333";
    protected String polyEdgeColor = "#CC4C4C";
    protected String polyPointColor = "#33CCCC";
    protected String ellipsoidGridColor = "#CC4C4C";
    protected String ellipsoidPointColor = "#CCCC33";
    protected String cylinderGridColor = "#CC3333";
    protected String cylinderEdgeColor = "#CC4C4C";
    protected String cylinderPointColor = "#CC33CC";
    protected String updateFile = "https://raw.github.com/yetanotherx/WorldEditCUI/master/updates.yml";
    protected Configuration config = null;

    public CUIConfiguration(WorldEditCUI controller) {
        this.controller = controller;
    }

    /**
     * Copies the default config file to the proper directory if it does not
     * exist. It then reads the file and sets each variable to the proper value.
     */
    @Override
    public void initialize() {

        File file = new File(WorldEditCUI.getWorldEditCUIDir(), "Configuration.yml");
        file.getParentFile().mkdirs();

        if (!file.exists()) {
            InputStream input = CUIConfiguration.class.getResourceAsStream("/Configuration.yml");
            if (input != null) {
                FileOutputStream output = null;

                try {
                    output = new FileOutputStream(file);
                    byte[] buf = new byte[8192];
                    int length = 0;
                    while ((length = input.read(buf)) > 0) {
                        output.write(buf, 0, length);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        input.close();
                    } catch (IOException e) {
                    }

                    try {
                        if (output != null) {
                            output.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }

        config = new Configuration(file);
        config.load();

        this.debugMode = config.getBoolean("debug", debugMode);
        this.ignoreUpdates = config.getBoolean("ignoreUpdates", ignoreUpdates);

        this.cuboidGridColor = parseColor(config.getString("colors.cuboidGrid"), this.cuboidGridColor);
        this.cuboidEdgeColor = parseColor(config.getString("colors.cuboidEdge"), this.cuboidEdgeColor);
        this.cuboidFirstPointColor = parseColor(config.getString("colors.cuboidFirstPoint"), this.cuboidFirstPointColor);
        this.cuboidSecondPointColor = parseColor(config.getString("colors.cuboidSecondPoint"), this.cuboidSecondPointColor);
        this.polyGridColor = parseColor(config.getString("colors.polyGrid"), this.polyGridColor);
        this.polyEdgeColor = parseColor(config.getString("colors.polyEdge"), this.polyEdgeColor);
        this.polyPointColor = parseColor(config.getString("colors.polyPoint"), this.polyPointColor);
        this.ellipsoidGridColor = parseColor(config.getString("colors.ellipsoidGrid"), this.ellipsoidGridColor);
        this.ellipsoidPointColor = parseColor(config.getString("colors.ellipsoidPoint"), this.ellipsoidPointColor);
        this.cylinderGridColor = parseColor(config.getString("colors.cylinderGrid"), this.cylinderGridColor);
        this.cylinderEdgeColor = parseColor(config.getString("colors.cylinderEdge"), this.cylinderEdgeColor);
        this.cylinderPointColor = parseColor(config.getString("colors.cylinderPoint"), this.cylinderPointColor);

        LineColor.CUBOIDBOX.setColor(this.cuboidEdgeColor);
        LineColor.CUBOIDGRID.setColor(this.cuboidGridColor);
        LineColor.CUBOIDPOINT1.setColor(this.cuboidFirstPointColor);
        LineColor.CUBOIDPOINT2.setColor(this.cuboidSecondPointColor);
        LineColor.POLYGRID.setColor(this.polyGridColor);
        LineColor.POLYBOX.setColor(this.polyEdgeColor);
        LineColor.POLYPOINT.setColor(this.polyPointColor);
        LineColor.ELLIPSOIDGRID.setColor(this.ellipsoidGridColor);
        LineColor.ELLIPSOIDCENTER.setColor(this.ellipsoidPointColor);
        LineColor.CYLINDERGRID.setColor(this.cylinderGridColor);
        LineColor.CYLINDERBOX.setColor(this.cylinderEdgeColor);
        LineColor.CYLINDERCENTER.setColor(this.cylinderPointColor);

        this.updateFile = config.getString("updateFile", this.updateFile);
    }

    /**
     * Validates a user-entered color code. Ensures that color is not null, it
     * starts with #, that it has all 6 digits, and that each hex code is valid.
     * @param color
     * @param def
     * @return 
     */
    protected String parseColor(String color, String def) {
        if (color == null) {
            return def;
        } else if (!color.startsWith("#")) {
            return def;
        } else if (color.length() != 7) {
            return def;
        }

        // Replaced some bloody stupid code with regex
        return (color.matches("(?i)^[0-9a-f]{6}$")) ? color : def;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public boolean ignoreUpdates() {
        return ignoreUpdates;
    }

    public String getUpdateFile() {
        return updateFile;
    }
}
