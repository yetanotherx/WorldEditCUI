package wecui.obfuscation;

import deobf.EntityPlayerSP;
import deobf.GuiChat;
import deobf.GuiScreen;
import deobf.Tessellator;
import java.io.File;
import java.lang.reflect.Method;
import net.minecraft.client.Minecraft;
import wecui.InitializationFactory;
import wecui.WorldEditCUI;

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
public class Obfuscation implements InitializationFactory {

    protected WorldEditCUI controller;
    protected Minecraft minecraft;
    protected Tessellator tessellator = Tessellator.a;

    public Obfuscation(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public void initialize() {
        this.minecraft = this.controller.getMinecraft();
    }

    public boolean isMultiplayerWorld() {
        return minecraft.l();
    }

    public void sendChat(String chat) {
        minecraft.h.a(chat);
    }
    
    public void showChatMessage(String chat) {
        minecraft.h.b(chat);
    }
    
    public void showGuiScreenIfGuiChat(GuiScreen screen) {
        GuiScreen currentScreen = minecraft.s;
        if( currentScreen == null || currentScreen.getClass() == GuiChat.class ) {
            try {
                Method setScreen = minecraft.getClass().getDeclaredMethod("a", GuiScreen.class);
                setScreen.invoke(minecraft, (GuiScreen)null);
                setScreen.invoke(minecraft, screen);
            } catch (Exception ex) {
            }
        }
    }

    public void draw_begin(int type) {
        tessellator.a(type);
    }

    public double getPlayerX(float renderTick) {
        EntityPlayerSP plyr = minecraft.h;
        return plyr.p + ((plyr.s - plyr.p) * renderTick);
    }

    public double getPlayerY(float renderTick) {
        EntityPlayerSP plyr = minecraft.h;
        return plyr.q + ((plyr.t - plyr.q) * renderTick);
    }

    public double getPlayerZ(float renderTick) {
        EntityPlayerSP plyr = minecraft.h;
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

    public void switchToInGame() {
        minecraft.a((GuiScreen)null);
        minecraft.g();
    }
}
