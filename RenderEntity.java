import net.minecraft.client.Minecraft;

/**
 * 
 */

/**
 * @author lahwran
 *
 */
public class RenderEntity extends si {

    
    Minecraft mc;
    /**
     * @param arg0
     */
    public RenderEntity(Minecraft mc, fb arg0)
    {
        super(arg0);
        bL=true; //ignoreFrustumCheck \o/
        System.out.println("Entity spawned");
        this.mc = mc;
    }

    @Override
    protected void a(nq arg0){} //Entity.readEntityFromNBT

    @Override
    protected void b(){} //Entity.entityInit

    @Override
    protected void b(nq arg0){}
    
    public void w_()
    {
        this.d(mc.h.aM, mc.h.aN, mc.h.aO);
    }
    
    public void K() {}
}
