package deobf;

import wecui.WorldEditCUI;
import wecui.render.RenderEntity;
import wecui.render.RenderHooks;
import java.util.Map;
import java.util.Vector;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import wecui.SPCWorldEditCUI;
import wecui.gui.WorldEditScreen;
import wecui.obfuscation.DataPacketList;
import wecui.obfuscation.Obfuscation;
import wecui.vendor.org.joor.Reflect;

/**
 * Main ModLoader class. Initializes the mod, enabling CUI communication 
 * between server and client, in addition to enabling rendering.
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class mod_WorldEditCUI extends BaseMod {

    protected WorldEditCUI controller;
    protected World lastWorld;
    protected EntityPlayerSP lastPlayer;
    protected RenderEntity renderEntity;
    protected KeyBinding guiKey;
    protected boolean spcInitialized = false;

    public mod_WorldEditCUI() {
        this.controller = new WorldEditCUI(ModLoader.getMinecraftInstance());
        this.controller.initialize();

        this.guiKey = new KeyBinding("CUIKey", Keyboard.KEY_G);
        
        ModLoader.setInGameHook(this, true, true); // the last true is because we don't want to iterate the entity list too often
        ModLoader.registerKey(this, guiKey, false);
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

            if (!spcInitialized) {
                spcInitialized = true;
                
                try {
                    //Loads the SPC class, unless SPC isn't installed. 
                    //Doing Class.forName will throw an exception if it's not found, 
                    //so only set the plugin if it doesn't throw an exception.
                    Class.forName("SPCPlugin");
                    
                    Vector pluginList = (Vector) Reflect.on(SPCPluginManager.getPluginManager()).get("plugins");
                    pluginList.add(new SPCWorldEditCUI(controller));
                    
                    controller.getLocalPlugin().onVersionEvent("");
                } catch (ClassNotFoundException e) {
                    controller.getDebugger().debug("SinglePlayerCommands not found, not worrying about the spc_WorldEditCUI class.");
                }
            }

            DataPacketList.register(controller);
        }
        return true;
    }

    /**
     * Shows a new WorldEdit GUI screen when the GUI key is pressed.
     * 
     */
    @Override
    public void keyboardEvent(KeyBinding event) {
        if (event.equals(guiKey) && controller.getObfuscation().getCurrentScreen() == null) {
            controller.getObfuscation().showGuiScreen(new WorldEditScreen(controller));
        }
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
