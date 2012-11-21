package wecui.render;

import deobf.Entity;
import deobf.Render;
import wecui.event.WorldRenderEvent;
import wecui.WorldEditCUI;
import wecui.obfuscation.RenderObfuscation;

/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated 1.4.5
 */
public class RenderHooks extends Render {

    protected WorldEditCUI controller;
    protected WorldRenderEvent event;

    public RenderHooks(WorldEditCUI controller) {
        this.controller = controller;
        this.event = new WorldRenderEvent(controller);
    }

    /**
     * Actually renders the entity.
     * @param entity
     * @param x
     * @param y
     * @param z
     * @param yaw
     * @param renderTick 
     */
    public void renderCUI(Entity entity, double x, double y, double z, float yaw, float renderTick) {
        RenderObfuscation.disableLighting();
        event.setPartialTick(renderTick);
        controller.getEventManager().callEvent(event);
        RenderObfuscation.enableLighting();
    }

    /**
     * Render.doRender
     * @param entity
     * @param x
     * @param y
     * @param z
     * @param yaw
     * @param renderTick 
     */
    @Override
    public void a(Entity entity, double x, double y, double z, float yaw, float renderTick) {
        renderCUI(entity, x, y, z, yaw, renderTick);
    }
}
