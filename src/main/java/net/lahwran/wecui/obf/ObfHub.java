/**
 * 
 */
package net.lahwran.wecui.obf;

import java.io.File;

import deobf.kj;
import deobf.xe;
import net.minecraft.client.Minecraft;

/**
 * @author lahwran
 *
 */
public class ObfHub {

    public static ObfHub inst;

    private Minecraft minecraft;

    private xe tessellator = xe.a;
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
        kj plyr = minecraft.h;
        return plyr.l + ((plyr.o - plyr.l) * renderTick);
    }
    public double getPlayerY(float renderTick) {
        kj plyr = minecraft.h;
        return plyr.m + ((plyr.p - plyr.m) * renderTick);
    }
    public double getPlayerZ(float renderTick) {
        kj plyr = minecraft.h;
        return plyr.n + ((plyr.q - plyr.n) * renderTick);
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
