import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;


public class RenderHooks extends bu {

	public void render(float renderTick) {
		((t)null).a();
		if (arrhooks == null) { arrhooks = hooks.toArray(new Renderhook[0]); }
		Minecraft game = ModLoader.getMinecraftInstance();
		for (int i=0; i<arrhooks.length; i++)
		{
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			arrhooks[i].worldRender(game, renderTick);
		}
		((t)null).b();
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
    /* (non-Javadoc)
     * @see bu#a(si, double, double, double, float, float)
     */
    @Override
    public void a(si arg0, double arg1, double arg2, double arg3, float arg4, float arg5)
    {
        render(arg5);
    }

}
