package wecui.obfuscation;

import deobf.Entity;
import deobf.EntityClientPlayerMP;
import deobf.EntityPlayerSP;
import deobf.MCHash;
import deobf.NetClientHandler;
import deobf.Packet250CustomPayload;
import deobf.Packet3Chat;
import deobf.WorldClient;
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
 * @obfuscated 1.3.2
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
        return true; // TODO - Temprarily until I can figure out the new server thing
        //return minecraft.l();
    }

    /**
     * Displays a chat message on the screen, if the player is currently playing
     * @param chat 
     */
    public void showChatMessage(String chat) {
        if (getPlayer() != null) {
            getPlayer().c(chat);
        }
    }

    public EntityPlayerSP getPlayer() {
        return getPlayer(minecraft);
    }

    public WorldClient getWorld() {
        return getWorld(minecraft);
    }

    public Entity spawnEntity() {
        Minecraft mc = this.controller.getMinecraft();

        Entity entity = new RenderEntity(this.controller, getWorld(mc));
        setEntityPositionToPlayer(mc, entity);
        getWorld(mc).d(entity);
        setEntityPositionToPlayer(mc, entity);
        controller.getDebugger().debug("RenderEntity spawned");
        return entity;
    }

    public static double getPlayerX(EntityPlayerSP player) {
        return player.t;
    }

    public static double getPlayerY(EntityPlayerSP player) {
        return player.u;
    }

    public static double getPlayerZ(EntityPlayerSP player) {
        return player.v;
    }

    public double getPlayerXGuess(float renderTick) {
        EntityPlayerSP plyr = getPlayer();
        return plyr.q + ((plyr.t - plyr.q) * renderTick);
    }

    public double getPlayerYGuess(float renderTick) {
        EntityPlayerSP plyr = getPlayer();
        return plyr.r + ((plyr.u - plyr.r) * renderTick);
    }

    public double getPlayerZGuess(float renderTick) {
        EntityPlayerSP plyr = getPlayer();
        return plyr.s + ((plyr.v - plyr.s) * renderTick);
    }

    public static EntityPlayerSP getPlayer(Minecraft mc) {
        return mc.g;
    }

    public static WorldClient getWorld(Minecraft mc) {
        return mc.e;
    }

    public static void setEntityPositionToPlayer(Minecraft mc, Entity entity) {
        entity.d(getPlayerX(mc.g), getPlayerY(mc.g), getPlayerZ(mc.g));
    }

    public NetClientHandler getNetClientHandler(EntityClientPlayerMP player) {
        return player.a;
    }

    public static String getChatFromPacket(Packet3Chat packet) {
        return packet.b;
    }

    public static byte[] getBytesFromPacket(Packet250CustomPayload packet) {
        return packet.c;
    }

    public static Packet250CustomPayload newPayloadPacket(String name, int len, byte[] data) {
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.a = name;
        packet.b = len;
        packet.c = data;
        return packet;
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
