package wecui.render;

import deobf.Entity;
import deobf.NBTTagCompound;
import deobf.World;
import wecui.WorldEditCUI;
import wecui.obfuscation.Obfuscation;

/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated 1.2
 */
public class RenderEntity extends Entity {

    protected WorldEditCUI controller;

    public RenderEntity(WorldEditCUI controller, World world) {
        super(world);
        this.controller = controller;
        ak = true; // ignoreFrustumCheck \o/
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
    public void I_() {
        Obfuscation.setEntityPositionToPlayer(controller.getMinecraft(), this);
    }

    @Override
    public void z() {
    }
}
