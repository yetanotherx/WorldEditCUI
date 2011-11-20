package deobf;

import java.util.Map;

import net.lahwran.wecui.WorldEditCUI;
import net.lahwran.wecui.obf.RenderEntity;
import net.lahwran.wecui.obf.RenderHooks;
import net.minecraft.client.Minecraft;

import deobf.kj;

public class mod_WorldEditCUI extends BaseMod {
    static {
        WorldEditCUI.initialize(ModLoader.getMinecraftInstance());
    }
    public String Version() {
        return WorldEditCUI.version;
    }

    public mod_WorldEditCUI() {
        ModLoader.SetInGameHook(this, true, true); // the last true is because we don't want to iterate the entity list too often
    }

    public static rv lastworld = null;
    public static RenderEntity entity;

    public static void spawn(Minecraft mc) {
        entity = new RenderEntity(mc, mc.f);
        entity.d(mc.h.o, mc.h.p, mc.h.q);
        mc.f.a((kj) entity);
        entity.d(mc.h.o, mc.h.p, mc.h.q);
        WorldEditCUI.debug("spawned render entity");
    }

    public boolean OnTickInGame(Minecraft mc) {
        if (mc.f != lastworld) {
            spawn(mc);
            lastworld = mc.f;
        }
        return true;
    }

    
    public void AddRenderer(Map map) {
        WorldEditCUI.debug("Attaching worldeditcui renderer");
        map.put(RenderEntity.class, new RenderHooks());
    }/**/
}
