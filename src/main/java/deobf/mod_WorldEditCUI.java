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

    public static ry lastworld;
    public static di lastplayer;
    public static RenderEntity entity;

    @Override
    public boolean OnTickInGame(float partialticks, Minecraft mc) {
        if (mc.f != lastworld || mc.h != lastplayer) {

            entity = new RenderEntity(mc, mc.f);
            entity.d(mc.h.s, mc.h.t, mc.h.u);
            mc.f.a((ia) entity);
            entity.d(mc.h.s, mc.h.t, mc.h.u);
            WorldEditCUI.getDebugger().debug("RenderEntity spawned");

            lastworld = mc.f;
            lastplayer = mc.h;
        }
        return true;
    }

    @Override
    public void load() {
        WorldEditCUI.setInstance(ModLoader.getMinecraftInstance());
        ModLoader.SetInGameHook(this, true, true); // the last true is because we don't want to iterate the entity list too often
    }

    @Override
    @SuppressWarnings("unchecked")
    public void AddRenderer(Map map) {
        WorldEditCUI.getDebugger().debug("Attaching renderer to ModLoader");
        map.put(RenderEntity.class, new RenderHooks());
    }

    @Override
    public String getVersion() {
        return WorldEditCUI.VERSION;
    }
}
