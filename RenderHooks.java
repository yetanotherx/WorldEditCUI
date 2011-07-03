import java.util.ArrayList;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

public class RenderHooks extends bw {

    @SuppressWarnings("static-access")
    public void render(float renderTick) {
        ((u) null).a();
        if (arrhooks == null) {
            arrhooks = hooks.toArray(new Renderhook[0]);
        }
        Minecraft game = ModLoader.getMinecraftInstance();
        for (int i = 0; i < arrhooks.length; i++) {
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            arrhooks[i].worldRender(game, renderTick);
        }
        ((u) null).b();
    }

    protected static Renderhook[]          arrhooks;
    protected static ArrayList<Renderhook> hooks = new ArrayList<Renderhook>();

    public static void addHook(Renderhook hook) {
        hooks.add(hook);
        arrhooks = hooks.toArray(new Renderhook[0]);
    }

    public static void delHook(Renderhook hook) {
        hooks.remove(hook);
        arrhooks = hooks.toArray(new Renderhook[0]);
    }

    @Override
    public void a(sn arg0, double arg1, double arg2, double arg3, float arg4, float arg5) {
        render(arg5);
    }

}
