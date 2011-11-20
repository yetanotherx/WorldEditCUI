package net.lahwran.wecui.obf;
import java.util.ArrayList;

import net.lahwran.WorldRenderEvent;
import net.lahwran.fevents.EventManager;
import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

import deobf.ia;
import deobf.rt;
import deobf.rg;

public class RenderHooks extends rg {

    public RenderHooks()
    {
        System.out.println("Attaching worldeditcui renderer step 2");
    }

    private void render(float renderTick) {
        rt.a();
        WorldRenderEvent renderEvent = WorldRenderEvent.update(renderTick);
        EventManager.callEvent(renderEvent);
        rt.b();
    }

    @Override
    public void a(ia arg0, double arg1, double arg2, double arg3, float arg4, float arg5) {
        render(arg5);
    }

}
