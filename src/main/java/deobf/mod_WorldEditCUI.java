package deobf;

import wecui.WorldEditCUI;
import wecui.render.RenderEntity;
import wecui.render.RenderHooks;
import java.util.Map;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import wecui.gui.WorldEditScreen;
import wecui.obfuscation.DataPacketList;
import wecui.obfuscation.Obfuscation;

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

    @Override
    public void load() {
        this.controller = new WorldEditCUI(ModLoader.getMinecraftInstance());
        this.controller.initialize();
        this.guiKey = new KeyBinding("CUIKey", Keyboard.getKeyIndex(this.controller.getSettings().getProperty("guiKey")));
        ModLoader.SetInGameHook(this, true, true); // the last true is because we don't want to iterate the entity list too often
        ModLoader.RegisterKey(this, guiKey, false);
    }

    @Override
    public boolean OnTickInGame(float partialticks, Minecraft mc) {

        //Checks if the world or player has changed from the last time we checked.
        //If it's changed, spawn a new render entity and update accordingly.
        if (Obfuscation.getWorld(mc) != lastWorld || Obfuscation.getPlayer(mc) != lastPlayer) {

            controller.getObfuscation().spawnEntity(renderEntity);

            lastWorld = Obfuscation.getWorld(mc);
            lastPlayer = Obfuscation.getPlayer(mc);

            DataPacketList.register(controller);
        }
        return true;
    }

    @Override
    public void KeyboardEvent(KeyBinding event) {
        
        //Shows a new WorldEdit GUI screen when the GUI key is pressed.
        //TODO: Merge this with regular chat?
        if (event.equals(guiKey) && controller.getObfuscation().getCurrentScreen() == null ) {
            //TODO: Stray G key?
            //TODO: This is broken
            controller.getObfuscation().showGuiScreenIfGuiChat(new WorldEditScreen(controller));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void AddRenderer(Map map) {
        controller.getDebugger().debug("Attaching renderer to ModLoader");
        map.put(RenderEntity.class, new RenderHooks(controller));
    }

    @Override
    public String getVersion() {
        return WorldEditCUI.VERSION;
    }
}
