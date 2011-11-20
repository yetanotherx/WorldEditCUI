package net.lahwran.wecui.obf;
import deobf.ia;
import deobf.ry;
import deobf.ik;
import net.lahwran.wecui.WorldEditCUI;
import net.minecraft.client.Minecraft;

/**
 * 
 */

/**
 * @author lahwran
 * 
 */
public class RenderEntity extends ia {

    private Minecraft mc;
    /**
     * @param arg0
     */
    public RenderEntity(Minecraft mc, ry arg0) {
        super(arg0);
        ao = true; // ignoreFrustumCheck \o/
        this.mc = mc;
        WorldEditCUI.debug("Entity spawned");
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
        this.d(mc.h.o, mc.h.p, mc.h.q);
    }

    @Override
    public void v() {
    }
}
