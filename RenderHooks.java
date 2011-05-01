import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;


public class RenderHooks extends EntityRendererProxy {

	Method setupCameraTransform;
	Method renderHudItem;
	Field zoom;
	public RenderHooks(Minecraft minecraft) {
		super(minecraft);
		try {
			setupCameraTransform=oy.class.getDeclaredMethod("a", float.class, int.class);
			renderHudItem=oy.class.getDeclaredMethod("b", float.class, int.class);
			zoom=oy.class.getDeclaredField("C");
		}  catch (Throwable e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		renderHudItem.setAccessible(true);
		setupCameraTransform.setAccessible(true);
		zoom.setAccessible(true);
	}
	
	//copied from mw.c
	public void c(float renderTick) {
		double prevzoom = 1.0;
		try {
			prevzoom = zoom.getDouble(this);
		
    		zoom.set(this, (Double) 1.001);
    		super.c(renderTick);
    		zoom.set(this, (Double) prevzoom);
		} catch (Throwable e) {
            throw new RuntimeException(e);
        }
		
		try {
			setupCameraTransform.invoke(this, renderTick, 0);
		}  catch (Throwable e) {
			throw new RuntimeException(e);
		}
		a(0);
		((t)null).a();
		if (arrhooks == null) { arrhooks = hooks.toArray(new Renderhook[0]); }
		Minecraft game = ModLoader.getMinecraftInstance();
		for (int i=0; i<arrhooks.length; i++)
		{
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			arrhooks[i].worldRender(game, renderTick);
		}
		((t)null).b();
		ty targetblock = game.x;
		kw entityliving = game.h;
		if((entityliving instanceof gh) && targetblock != null && !game.h.a(kr.f)) {
            gh gh2 = (gh)entityliving;
            GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
            m renderglobal = game.f;
            renderglobal.a(gh2, targetblock, 0, gh2.f.b(), renderTick);
            renderglobal.b(gh2, targetblock, 0, gh2.f.b(), renderTick);
            GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
        }
		GL11.glClear(256);
        try {
			renderHudItem.invoke(this, renderTick, 0);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	protected static Renderhook[] arrhooks;
	protected static ArrayList<Renderhook> hooks = new ArrayList<Renderhook>();
	
	public static void addHook(Renderhook hook)
	{
		hooks.add(hook);
		arrhooks = hooks.toArray(new Renderhook[0]);
	}
	public static void delHook(Renderhook hook)
	{
		hooks.remove(hook);
		arrhooks = hooks.toArray(new Renderhook[0]);
	}

}
