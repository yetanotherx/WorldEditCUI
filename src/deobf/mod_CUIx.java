package deobf;

import cuix.CUIx;
import cuix.obfuscation.RenderEntity;
import cuix.obfuscation.RenderHooks;
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
public class mod_CUIx extends BaseMod {

    public mod_CUIx() {
        CUIx.setInstance(ModLoader.getMinecraftInstance());
        ModLoader.SetInGameHook(this, true, true); // the last true is because we don't want to iterate the entity list too often
        CUIx.getDebugger().info("CUIx version " + CUIx.VERSION + " enabled!");
    }
    public static ry lastworld;
    public static di lastplayer;
    public static RenderEntity entity;

    public static void spawn(Minecraft mc) {
        entity = new RenderEntity(mc, mc.f);
        entity.d(mc.h.s, mc.h.t, mc.h.u);
        mc.f.a((ia) entity);
        entity.d(mc.h.s, mc.h.t, mc.h.u);
        CUIx.getDebugger().debug("RenderEntity spawned");
    }

    @Override
    public boolean OnTickInGame(float partialticks, Minecraft mc) {
        if (mc.f != lastworld || mc.h != lastplayer) {
            spawn(mc);
            lastworld = mc.f;
            lastplayer = mc.h;
        }
        return true;
    }

    @Override
    public void load() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void AddRenderer(Map map) {
        CUIx.getDebugger().debug("Attaching renderer to ModLoader");
        map.put(RenderEntity.class, new RenderHooks());
    }


    @Override
    public String getVersion() {
        return CUIx.VERSION;
    }
}
