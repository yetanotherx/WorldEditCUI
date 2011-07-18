import net.minecraft.client.Minecraft;

/**
 * 
 */

/**
 * @author lahwran
 * 
 */
public class RenderEntity extends sn {

    Minecraft mc;

    /**
     * @param arg0
     */
    public RenderEntity(Minecraft mc, fd arg0) {
        super(arg0);
        bM = true; // ignoreFrustumCheck \o/
        // Do not run debug messages on a live compile!
        if(mod_WorldEditCUI.WORLDEDIT_CUI_DEBUG) System.out.println("Entity spawned");
        this.mc = mc;
    }

    @Override
    protected void a(nu arg0) {
    } // Entity.readEntityFromNBT

    @Override
    protected void b() {
    } // Entity.entityInit

    @Override
    protected void b(nu arg0) {
    }

    @Override
    public void w_() {
        this.e(mc.h.aM, mc.h.aN, mc.h.aO);
    }

    @Override
    public void K() {
    }
}
