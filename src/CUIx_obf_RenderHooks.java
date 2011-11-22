
/**
 * Custom entity renderer, attached in the ModLoader class
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 * @obfuscated
 */
public class CUIx_obf_RenderHooks extends rg {

    public CUIx_obf_RenderHooks() {
        System.out.println("Attaching worldeditcui renderer step 2");
    }

    private void render(float renderTick) {
        rt.a();
        CUIx_events_WorldRenderEvent renderEvent = CUIx_events_WorldRenderEvent.setTickAndGetInstance(renderTick);
        CUIx_fevent_EventManager.callEvent(renderEvent);
        rt.b();
    }

    @Override
    public void a(ia arg0, double arg1, double arg2, double arg3, float arg4, float arg5) {
        render(arg5);
    }
}
