package net.lahwran.wecui.obf;
import java.util.ArrayList;

import net.lahwran.WorldRenderEvent;
import net.lahwran.fevents.EventManager;
import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

import deobf.kj;
import deobf.ow;
import deobf.px;

public class RenderHooks extends px {

    public RenderHooks()
    {
        System.out.println("Attaching worldeditcui renderer step 2");
    }

    private void render(float renderTick) {
        ow.a();
        WorldRenderEvent renderEvent = WorldRenderEvent.update(renderTick);
        EventManager.callEvent(renderEvent);
        ow.b();
    }

    @Override
    public void a(kj arg0, double arg1, double arg2, double arg3, float arg4, float arg5) {
        render(arg5);
    }

}
