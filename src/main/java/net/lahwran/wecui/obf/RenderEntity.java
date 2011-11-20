package net.lahwran.wecui.obf;
import deobf.kj;
import deobf.rv;
import deobf.xb;
import net.lahwran.wecui.WorldEditCUI;
import net.minecraft.client.Minecraft;

/**
 * 
 */

/**
 * @author lahwran
 * 
 */
public class RenderEntity extends kj {

    private Minecraft mc;
    /**
     * @param arg0
     */
    public RenderEntity(Minecraft mc, rv arg0) {
        super(arg0);
        an = true; // ignoreFrustumCheck \o/
        this.mc = mc;
        WorldEditCUI.debug("Entity spawned");
    }

    @Override
    protected void a(xb arg0) {
    } // Entity.readEntityFromNBT

    @Override
    protected void b() {
    } // Entity.entityInit

    @Override
    protected void b(xb arg0) {
    }

    @Override
    public void w_() {
        this.d(mc.h.o, mc.h.p, mc.h.q);
    }

    @Override
    public void v() {
    }
}
