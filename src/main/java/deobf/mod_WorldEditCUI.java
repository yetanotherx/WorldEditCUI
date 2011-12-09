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
    protected World lastworld;
    protected EntityPlayerSP lastplayer;
    protected RenderEntity entity;
    protected KeyBinding key;

    public void mod_WorldEditCUI() {
    }

    @Override
    public void load() {
        this.controller = new WorldEditCUI(ModLoader.getMinecraftInstance());
        this.controller.initialize();
        //TODO Settings doesn't work?
        this.key = new KeyBinding("CUIKey", Keyboard.getKeyIndex(this.controller.getSettings().getProperty("guiKey")));
        ModLoader.SetInGameHook(this, true, true); // the last true is because we don't want to iterate the entity list too often
        ModLoader.RegisterKey(this, key, false);
    }

    @Override
    public boolean OnTickInGame(float partialticks, Minecraft mc) {

        //Checks if the world or player has changed from the last time we checked.
        //If it's changed, spawn a new render entity and update accordingly.
        //Boy, don't obfuscated methods make this fun.
        if (Obfuscation.getWorld(mc) != lastworld || Obfuscation.getPlayer(mc) != lastplayer) {

            controller.getObfuscation().spawnEntity(entity);

            lastworld = Obfuscation.getWorld(mc);
            lastplayer = Obfuscation.getPlayer(mc);

            DataPacketList.register(controller);
        }
        return true;
    }

    @Override
    public void KeyboardEvent(KeyBinding event) {
        if (event.equals(key)) {
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
