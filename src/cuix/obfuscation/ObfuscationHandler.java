package cuix.obfuscation;

import deobf.cv;
import deobf.di;
import java.io.File;
import net.minecraft.client.Minecraft;

/**
 * Main obfuscation class
 * Combines all obfuscated classes and methods into a single class
 * Eases updates, cleans up the rest of the codebase.
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated
 */
public class ObfuscationHandler {

    public static ObfuscationHandler instance;
    private Minecraft minecraft;
    private cv tessellator = cv.a;

    /**
     * @param minecraft
     */
    public ObfuscationHandler(Minecraft minecraft) {
        this.minecraft = minecraft;
        this.instance = this;
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

    public static File getMinecraftDir() {
        return Minecraft.b();
    }
}
