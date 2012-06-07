package wecui.obfuscation;

import deobf.Block;
import deobf.Entity;
import deobf.EntityClientPlayerMP;
import deobf.EntityPlayerSP;
import deobf.GuiScreen;
import deobf.MCHash;
import deobf.NetClientHandler;
import deobf.Packet3Chat;
import deobf.World;
import java.io.File;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
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
 * @obfuscated 1.2.5
 */
public class Obfuscation implements InitializationFactory {

    protected WorldEditCUI controller;
    protected Minecraft minecraft;

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

    /**
     * Displays a chat message on the screen, if the player is currently playing
     * @param chat 
     */
    public void showChatMessage(String chat) {
        if (getPlayer() != null) {
            getPlayer().b(chat);
        }
    }

    public void showGuiScreen(GuiScreen screen) {
        GuiScreen currentScreen = getCurrentScreen();
        if (currentScreen != null) {
            minecraft.a((GuiScreen) null);
        }
        minecraft.a(screen);
    }

    public double getPlayerXGuess(float renderTick) {
        EntityPlayerSP plyr = getPlayer();
        return plyr.l + ((plyr.o - plyr.l) * renderTick);
    }

    public double getPlayerYGuess(float renderTick) {
        EntityPlayerSP plyr = getPlayer();
        return plyr.m + ((plyr.p - plyr.m) * renderTick);
    }

    public double getPlayerZGuess(float renderTick) {
        EntityPlayerSP plyr = getPlayer();
        return plyr.n + ((plyr.q - plyr.n) * renderTick);
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

    public NetClientHandler getNetClientHandler(EntityClientPlayerMP player) {
        return player.cl;
    }

    public int getTexture(String file) {
        return minecraft.p.b(file);
    }

    public Block getBlockFromID(int id) {
        return Block.m[id];
    }

    public static double getPlayerX(EntityPlayerSP player) {
        return player.o;
    }

    public static double getPlayerY(EntityPlayerSP player) {
        return player.p;
    }

    public static double getPlayerZ(EntityPlayerSP player) {
        return player.q;
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

    public void translateToCamera(float partialTick) {
        GL11.glTranslated(-getPlayerXGuess(partialTick), -getPlayerYGuess(partialTick), -getPlayerZGuess(partialTick));
    }
}
