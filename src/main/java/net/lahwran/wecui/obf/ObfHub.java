/**
 * 
 */
package net.lahwran.wecui.obf;

import java.io.File;

import deobf.di;
import deobf.cv;
import net.minecraft.client.Minecraft;

/**
 * @author lahwran
 *
 */
public class ObfHub {

    public static ObfHub inst;

    private Minecraft minecraft;

    private cv tessellator = cv.a;
    /**
     * @param minecraft
     */
    public ObfHub(Minecraft minecraft) {
        this.minecraft = minecraft;
        inst = this;
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
