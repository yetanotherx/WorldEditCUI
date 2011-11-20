package deobf;

import java.util.Map;

import net.lahwran.wecui.WorldEditCUI;
import net.lahwran.wecui.obf.RenderEntity;
import net.lahwran.wecui.obf.RenderHooks;
import net.minecraft.client.Minecraft;

import deobf.ia;
import deobf.ry;

public class mod_WorldEditCUI extends BaseMod {
    static {
        WorldEditCUI.initialize(ModLoader.getMinecraftInstance());
    }

    @Override
    public String getVersion() {
        return WorldEditCUI.version;
    }

    public mod_WorldEditCUI() {
        ModLoader.SetInGameHook(this, true, true); // the last true is because we don't want to iterate the entity list too often
    }

    public static ry lastworld = null;
    public static RenderEntity entity;

    public static void spawn(Minecraft mc) {
        entity = new RenderEntity(mc, mc.f);
        entity.d(mc.h.s, mc.h.t, mc.h.u);
        mc.f.a((ia) entity);
        entity.d(mc.h.s, mc.h.t, mc.h.u);
        WorldEditCUI.debug("spawned render entity");
    }

    @Override
    public boolean OnTickInGame(Minecraft mc, float partialticks) {
        if (mc.f != lastworld) {
            spawn(mc);
            lastworld = mc.f;
        }
        return true;
    }

    @Override
    public void load() {}
    
    @Override
    public void AddRenderer(Map map) {
        WorldEditCUI.debug("Attaching worldeditcui renderer");
        map.put(RenderEntity.class, new RenderHooks());
    }/**/

}
