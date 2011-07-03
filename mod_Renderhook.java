import java.util.Map;

import net.minecraft.client.Minecraft;

public class mod_Renderhook extends BaseMod {

    public mod_Renderhook() {
        // ModLoader.getMinecraftInstance().s = new
        // RenderHooks(ModLoader.getMinecraftInstance());
        ModLoader.SetInGameHook(this, true, true); // the last true is because
                                                   // we don't want to iterate
                                                   // the entity list too often
    }

    @Override
    public String Version() {
        return "1.7_01";
    }

    public static fd           lastworld = null;
    public static RenderEntity entity;

    public static void spawn(Minecraft mc) {
        entity = new RenderEntity(mc, mc.f);
        entity.d(mc.h.aM, mc.h.aN, mc.h.aO);
        mc.f.a((sn) entity);
        entity.d(mc.h.aM, mc.h.aN, mc.h.aO);
        // Do not run debug messages on a live compile!
        if(mod_WorldEditCUI.WORLDEDIT_CUI_DEBUG) System.out.println("spawned render entity");
    }

    public boolean OnTickInGame(Minecraft mc) {
        if (mc.f != lastworld) {
            // do spawny stuff here
            spawn(mc);
            lastworld = mc.f;
        }
        return true;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void AddRenderer(Map map) {
        // Do not run debug messages on a live compile!
        if(mod_WorldEditCUI.WORLDEDIT_CUI_DEBUG) System.out.println("Attaching worldeditcui renderer");
        map.put(RenderEntity.class, new RenderHooks());
    }
}
