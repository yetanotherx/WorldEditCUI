
import java.io.File;

import net.minecraft.client.Minecraft;

/**
 * Main obfuscation class
 * Combines all obfuscated classes and methods into a single class
 * Eases updates, cleans up the rest of the codebase.
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIx_obf_Handler {

    public static CUIx_obf_Handler instance;
    private Minecraft minecraft;
    private cv tessellator = cv.a;

    /**
     * @param minecraft
     */
    public CUIx_obf_Handler(Minecraft minecraft) {
        this.minecraft = minecraft;
        instance = this;
    }

    public boolean isMultiplayerWorld() {
        return minecraft.l();
    }

    public void sendChat(String chat) {
        minecraft.h.a(chat);
    }

    public void draw_begin(int type) {
        tessellator.a(type);
    }

    public double getPlayerX(float renderTick) {
        di plyr = minecraft.h;
        return plyr.p + ((plyr.s - plyr.p) * renderTick);
    }

    public double getPlayerY(float renderTick) {
        di plyr = minecraft.h;
        return plyr.q + ((plyr.t - plyr.q) * renderTick);
    }

    public double getPlayerZ(float renderTick) {
        di plyr = minecraft.h;
        return plyr.r + ((plyr.u - plyr.r) * renderTick);
    }

    public void addVertex(double x, double y, double z) {
        tessellator.a(x, y, z);
    }

    public void draw() {
        tessellator.a();
    }

    public static File getAppDir(String app) {
        return Minecraft.a(app);
    }
}
