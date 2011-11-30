package wecui.obfuscation;

import wecui.event.WorldRenderEvent;
import wecui.fevents.EventManager;
import deobf.ia;
import deobf.rg;
import deobf.rt;
import wecui.WorldEditCUI;


/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated
 */
public class RenderHooks extends rg {

    protected WorldEditCUI controller;
    protected WorldRenderEvent event;

    public RenderHooks(WorldEditCUI controller) {
        this.controller = controller;
        this.event = new WorldRenderEvent(controller);
    }    

    private void render(float renderTick) {
        rt.a();
        event.setPartialTick(renderTick);
        controller.getEventManager().callEvent(event);
        rt.b();
    }

    @Override
    public void a(ia arg0, double arg1, double arg2, double arg3, float arg4, float arg5) {
        render(arg5);
    }
}
