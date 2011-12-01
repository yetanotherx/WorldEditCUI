package deobf;

import wecui.WorldEditCUI;
import wecui.obfuscation.RenderEntity;
import wecui.obfuscation.RenderHooks;
import java.util.Map;

import net.minecraft.client.Minecraft;

/**
 * Main ModLoader class. Initializes the mod, enabling CUI communication 
 * between server and client, in addition to enabling rendering.
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated
 * 
 */
public class mod_WorldEditCUI extends BaseMod {

    protected WorldEditCUI controller;
    protected World lastworld;
    protected EntityPlayerSP lastplayer;
    protected RenderEntity entity;

    public void mod_WorldEditCUI() {
    }

    @Override
    public void load() {
        this.controller = new WorldEditCUI(ModLoader.getMinecraftInstance());
        this.controller.initialize();
        ModLoader.SetInGameHook(this, true, true); // the last true is because we don't want to iterate the entity list too often
    }

    @Override
    public boolean OnTickInGame(float partialticks, Minecraft mc) {
        if (mc.f != lastworld || mc.h != lastplayer) {

            Minecraft newMC = this.controller.getMinecraft();
            
            entity = new RenderEntity(this.controller, newMC.f);
            entity.d(newMC.h.s, newMC.h.t, newMC.h.u);
            newMC.f.a((Entity) entity);
            entity.d(newMC.h.s, newMC.h.t, newMC.h.u);
            controller.getDebugger().debug("RenderEntity spawned");

            lastworld = newMC.f;
            lastplayer = newMC.h;
        }
        return true;
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
