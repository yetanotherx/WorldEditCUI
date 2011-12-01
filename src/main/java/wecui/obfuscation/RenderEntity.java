package wecui.obfuscation;

import deobf.Entity;
import deobf.NBTTagCompound;
import deobf.World;
import wecui.WorldEditCUI;
import net.minecraft.client.Minecraft;

/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated
 */
public class RenderEntity extends Entity {

    protected WorldEditCUI controller;

    public RenderEntity(WorldEditCUI controller, World arg0) {
        super(arg0);
        this.controller = controller;
        ao = true; // ignoreFrustumCheck \o/
        controller.getDebugger().debug("Entity spawned");
    }

    @Override
    protected void a(NBTTagCompound arg0) {
    } // Entity.readEntityFromNBT

    @Override
    protected void b() {
    } // Entity.entityInit

    @Override
    protected void b(NBTTagCompound arg0) {
    }

    @Override
    public void a() {
        Minecraft mc = controller.getMinecraft();
        this.d(mc.h.s, mc.h.t, mc.h.u);
    }

    @Override
    public void v() {
    }
}
