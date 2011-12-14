package wecui.obfuscation;

import deobf.Entity;
import deobf.EntityClientPlayerMP;
import deobf.EntityPlayerSP;
import deobf.GuiScreen;
import deobf.MCHash;
import deobf.NetClientHandler;
import deobf.Packet3Chat;
import deobf.RenderHelper;
import deobf.Tessellator;
import deobf.World;
import java.io.File;
import net.minecraft.client.Minecraft;
import wecui.InitializationFactory;
import wecui.WorldEditCUI;
import wecui.render.RenderEntity;

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
        getPlayer().a(chat);
    }

    /**
     * Displays a chat message on the screen, if the player is currently playing
     * @param chat 
     */
    public void showChatMessage(String chat) {
        if (getPlayer() != null) {
            getPlayer().b(chat);
        }
    }

    public void showGuiScreenIfGuiChat(GuiScreen screen) {
        GuiScreen currentScreen = getCurrentScreen();
        if (currentScreen != null) {
            minecraft.a((GuiScreen) null);
        }
        minecraft.a(screen);
    }

    public double getPlayerXGuess(float renderTick) {
        EntityPlayerSP plyr = getPlayer();
        return plyr.p + ((plyr.s - plyr.p) * renderTick);
    }

    public double getPlayerYGuess(float renderTick) {
        EntityPlayerSP plyr = getPlayer();
        return plyr.q + ((plyr.t - plyr.q) * renderTick);
    }

    public double getPlayerZGuess(float renderTick) {
        EntityPlayerSP plyr = getPlayer();
        return plyr.r + ((plyr.u - plyr.r) * renderTick);
    }

    public void startDrawing(int type) {
        tessellator.a(type);
    }

    public void addVertex(double x, double y, double z) {
        tessellator.a(x, y, z);
    }

    public void finishDrawing() {
        tessellator.a();
    }

    public EntityPlayerSP getPlayer() {
        return getPlayer(minecraft);
    }

    public World getWorld() {
        return getWorld(minecraft);
    }

    public GuiScreen getCurrentScreen() {
        return getCurrentScreen(minecraft);
    }

    public void spawnEntity(Entity entity) {
        Minecraft mc = this.controller.getMinecraft();

        entity = new RenderEntity(this.controller, getWorld(mc));
        setEntityPositionToPlayer(mc, entity);
        getWorld(mc).a(entity);
        setEntityPositionToPlayer(mc, entity);
        controller.getDebugger().debug("RenderEntity spawned");

    }

    public static double getPlayerX(EntityPlayerSP player) {
        return player.s;
    }

    public static double getPlayerY(EntityPlayerSP player) {
        return player.t;
    }

    public static double getPlayerZ(EntityPlayerSP player) {
        return player.u;
    }

    public static EntityPlayerSP getPlayer(Minecraft mc) {
        return mc.h;
    }

    public static World getWorld(Minecraft mc) {
        return mc.f;
    }
    
    public static GuiScreen getCurrentScreen(Minecraft mc) {
        return mc.s;
    }

    public static void setEntityPositionToPlayer(Minecraft mc, Entity entity) {
        entity.d(getPlayerX(mc.h), getPlayerY(mc.h), getPlayerZ(mc.h));
    }

    public static void disableLighting() {
        RenderHelper.a();
    }

    public static void enableLighting() {
        RenderHelper.b();
    }

    public NetClientHandler getNetClientHandler(EntityClientPlayerMP player) {
        return player.a;
    }

    public static String getChatFromPacket(Packet3Chat packet) {
        return packet.a;
    }

    public static void putToMCHash(MCHash hash, int first, Object second) {
        hash.a(first, second);
    }

    public static File getMinecraftDir() {
        return Minecraft.b();
    }

    public static File getWorldEditCUIDir() {
        return new File(getMinecraftDir(), "mods" + File.separator + "WorldEditCUI");
    }
}
