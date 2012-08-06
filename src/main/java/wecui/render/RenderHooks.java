package wecui.render;

import deobf.Entity;
import deobf.Render;
import wecui.event.WorldRenderEvent;
import wecui.WorldEditCUI;
import wecui.obfuscation.RenderObfuscation;
import wecui.util.Vector3;


/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated 1.3.1
 */
public class RenderHooks extends Render {

    protected WorldEditCUI controller;
    protected WorldRenderEvent event;

    public RenderHooks(WorldEditCUI controller) {
        this.controller = controller;
        this.event = new WorldRenderEvent(controller);
    }
    
    public void renderCUI(Entity entity, double x, double y, double z, float yaw, float renderTick) {
        RenderObfuscation.disableLighting();
        event.setPartialTick(renderTick);
        event.setPosition(new Vector3(x, y, z));
        controller.getEventManager().callEvent(event);
        RenderObfuscation.enableLighting();
    }

    @Override
    public void a(Entity entity, double d, double d1, double d2, float f, float f1) {
        renderCUI(entity, d, d1, d2, f, f1);
    }
}
