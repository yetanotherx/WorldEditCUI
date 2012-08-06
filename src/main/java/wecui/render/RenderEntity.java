package wecui.render;

import deobf.Entity;
import deobf.NBTTagCompound;
import deobf.WorldClient;
import deobf.Vec3;
import wecui.WorldEditCUI;
import wecui.obfuscation.Obfuscation;

/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated 1.3.1
 */
public class RenderEntity extends Entity {

    protected WorldEditCUI controller;

    public RenderEntity(WorldEditCUI controller, WorldClient world) {
        super(world);
        this.controller = controller;
        ak = true; // ignoreFrustumCheck \o/
        controller.getDebugger().debug("Entity spawned");
    }

    @Override
    protected void a(NBTTagCompound arg0) {
    } // Entity.readEntityFromNBT

    @Override
    protected void a() {
    } // Entity.entityInit

    @Override
    protected void b(NBTTagCompound arg0) {
    } // Entity.writeEntityToNBT
    
    @Override
    public void h_() {
        Obfuscation.setEntityPositionToPlayer(controller.getMinecraft(), this);
    } // Entity.onUpdate

    @Override
    public void y() {
    } // Entity.setDead

    @Override
    public String ak() {
        return "CUI";
    }

    @Override
    public boolean a(Vec3 vector) {
        return true;
    } // Entity.isInRangeToRenderVec3D
    
    
}
