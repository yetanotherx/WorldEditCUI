package deobf;

import java.nio.charset.Charset;
import wecui.WorldEditCUI;
import wecui.render.RenderEntity;
import wecui.render.RenderHooks;
import java.util.Map;

import net.minecraft.client.Minecraft;
import wecui.Updater;
import wecui.event.ChannelEvent;
import wecui.obfuscation.DataPacketList;
import wecui.obfuscation.Obfuscation;
import wecui.render.region.CuboidRegion;

/**
 * Main ModLoader class. Initializes the mod, enabling CUI communication 
 * between server and client, in addition to enabling rendering.
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated 1.2.5
 */
public class mod_WorldEditCUI extends BaseMod {

    protected WorldEditCUI controller;
    protected World lastWorld;
    protected EntityPlayerSP lastPlayer;
    protected RenderEntity renderEntity;
    protected boolean gameStarted = false;
    public final static Charset UTF_8_CHARSET = Charset.forName("UTF-8");

    public mod_WorldEditCUI() {
        this.controller = new WorldEditCUI(ModLoader.getMinecraftInstance());
        this.controller.initialize();

        ModLoader.setInGameHook(this, true, true); // the last true is because we don't want to iterate the entity list too often
        ModLoader.registerPacketChannel(this, "WECUI");
    }

    @Override
    public void load() {
    }

    /**
     * Checks if the world or player has changed from the last time we checked.
     * If it's changed, spawn a new render entity and update accordingly.
     * 
     * Additionally, initialize SPC plugin on first load. This is done now
     * because SPC only loads spc_Classes in the minecraft.jar file, and not
     * in the mods/ directory.
     * 
     * @param partialticks
     * @param mc
     * @return 
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean onTickInGame(float partialticks, Minecraft mc) {

        if (Obfuscation.getWorld(mc) != lastWorld || Obfuscation.getPlayer(mc) != lastPlayer) {
            controller.getObfuscation().spawnEntity(renderEntity);

            lastWorld = Obfuscation.getWorld(mc);
            lastPlayer = Obfuscation.getPlayer(mc);

            if (!gameStarted) {
                gameStarted = true;
                
                new Updater(controller).start();
                this.controller.setSelection(new CuboidRegion(controller));
                
                DataPacketList.register(controller);
            }
        }
        return true;
    }

    @Override
    public void receiveCustomPacket(Packet250CustomPayload packet) {
        ChannelEvent channelevent = new ChannelEvent(controller, new String(packet.c, UTF_8_CHARSET));
        controller.getEventManager().callEvent(channelevent);
    }

    @Override
    public void serverConnect(NetClientHandler handler) {
        byte[] buffer = ("v|" + WorldEditCUI.protocolVersion).getBytes(UTF_8_CHARSET);
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.a = "WECUI";
        packet.b = buffer.length;
        packet.c = buffer;
        ModLoader.sendPacket(packet);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addRenderer(Map map) {
        map.put(RenderEntity.class, new RenderHooks(controller));
    }

    @Override
    public String getVersion() {
        return WorldEditCUI.getVersion();
    }
    
}
