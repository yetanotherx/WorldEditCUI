package wecui.obfuscation;

import wecui.WorldEditCUI;
import deobf.ia;
import deobf.ik;
import deobf.ry;
import net.minecraft.client.Minecraft;

/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated
 */
public class RenderEntity extends ia {

    private Minecraft mc;

    public RenderEntity(Minecraft mc, ry arg0) {
        super(arg0);
        ao = true; // ignoreFrustumCheck \o/
        this.mc = mc;
        WorldEditCUI.getDebugger().debug("Entity spawned");
    }

    @Override
    protected void a(ik arg0) {
    } // Entity.readEntityFromNBT

    @Override
    protected void b() {
    } // Entity.entityInit

    @Override
    protected void b(ik arg0) {
    }

    @Override
    public void a() {
        this.d(mc.h.s, mc.h.t, mc.h.u);
    }

    @Override
    public void v() {
    }
}
