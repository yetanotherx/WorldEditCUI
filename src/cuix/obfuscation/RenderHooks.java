package cuix.obfuscation;

import cuix.event.WorldRenderEvent;
import cuix.fevents.EventManager;
import deobf.ia;
import deobf.rg;
import deobf.rt;


/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated
 */
public class RenderHooks extends rg {

    public RenderHooks() {
    }

    private void render(float renderTick) {
        rt.a();
        WorldRenderEvent renderEvent = WorldRenderEvent.setTickAndGetInstance(renderTick);
        EventManager.callEvent(renderEvent);
        rt.b();
    }

    @Override
    public void a(ia arg0, double arg1, double arg2, double arg3, float arg4, float arg5) {
        render(arg5);
    }
}
