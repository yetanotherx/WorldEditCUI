package wecui.render;

import deobf.Entity;
import deobf.NBTTagCompound;
import deobf.WorldClient;
import deobf.Vec3;
import wecui.WorldEditCUI;

/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated 1.4.2
 */
public class RenderEntity extends Entity {

    protected WorldEditCUI controller;

    public RenderEntity(WorldEditCUI controller, WorldClient world) {
        super(world);

        this.controller = controller;
        this.al = true; // Entity.ignoreFrustumCheck
        this.Y = true; // Entity.noClip
        this.b(0, 0); // Entity.setSize()
        controller.getDebugger().debug("Entity spawned");
    }

    /**
     * Entity.readEntityFromNBT
     * @param arg0 
     */
    @Override
    protected void a(NBTTagCompound arg0) {
    }

    /**
     * Entity.writeEntityToNBT
     * @param arg0 
     */
    @Override
    protected void b(NBTTagCompound arg0) {
    }

    /**
     * Entity.entityInit
     */
    @Override
    protected void a() {
    }

    /**
     * Entity.onUpdate
     */
    @Override
    public void j_() {
        //Obfuscation.setEntityPositionToPlayer(controller.getMinecraft(), this);
    }

    /**
     * Entity.setDead
     */
    @Override
    public void x() {
    }

    /**
     * Entity.whatEver()
     * Returns the name of the entity.
     * @return 
     */
    @Override
    public String an() {
        return "CUI";
    }

    /**
     * Entity.isInRangeToRenderVec3D
     * Always returns true, we want to render the entity no matter where we are.
     * @param vector
     * @return 
     */
    @Override
    public boolean a(Vec3 vector) {
        return true;
    }

    /**
     * Entity.getBrightnessForRender
     * Always return the maximum value, we want to always see it.
     * @param f
     * @return 
     */
    @Override
    public int b(float f) {
        return 0xf000f0;
    }

    /**
     * Entity.getBrightness
     * Always return the maximum value, we want to always see it.
     * @param f
     * @return 
     */
    @Override
    public float c(float f) {
        return 1f;
    }
}
