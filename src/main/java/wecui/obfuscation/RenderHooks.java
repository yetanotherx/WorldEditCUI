package wecui.obfuscation;

import deobf.Entity;
import deobf.Render;
import deobf.RenderHelper;
import wecui.event.WorldRenderEvent;
import wecui.WorldEditCUI;


/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated
 */
public class RenderHooks extends Render {

    protected WorldEditCUI controller;
    protected WorldRenderEvent event;

    public RenderHooks(WorldEditCUI controller) {
        this.controller = controller;
        this.event = new WorldRenderEvent(controller);
    }    

    private void render(float renderTick) {
        RenderHelper.a();
        event.setPartialTick(renderTick);
        controller.getEventManager().callEvent(event);
        RenderHelper.b();
    }

    @Override
    public void a(Entity arg0, double arg1, double arg2, double arg3, float arg4, float arg5) {
        render(arg5);
    }
}
