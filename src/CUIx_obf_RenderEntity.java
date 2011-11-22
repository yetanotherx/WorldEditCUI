
import net.minecraft.client.Minecraft;

/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated
 */
public class CUIx_obf_RenderEntity extends ia {

    private Minecraft mc;

    /**
     * @param arg0
     */
    public CUIx_obf_RenderEntity(Minecraft mc, ry arg0) {
        super(arg0);
        ao = true; // ignoreFrustumCheck \o/
        this.mc = mc;
        CUIx.debug("Entity spawned");
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
