import java.util.Map;

import net.minecraft.client.Minecraft;


public class mod_Renderhook extends BaseMod {

	public mod_Renderhook()
	{
		//ModLoader.getMinecraftInstance().s = new RenderHooks(ModLoader.getMinecraftInstance());
		ModLoader.SetInGameHook(this, true, true); //the last true is because we don't want to iterate the entity list too often
	}
	
	@Override
	public String Version() {
		// TODO Auto-generated method stub
		return "blarg";
	}
	public static fb lastworld = null;
	public static RenderEntity entity;
	public static void spawn(Minecraft mc)
	{
		entity = new RenderEntity(mc, mc.f);
		entity.d(mc.h.aM, mc.h.aN, mc.h.aO);
		mc.f.a((si)entity);
		entity.d(mc.h.aM, mc.h.aN, mc.h.aO);
		System.out.println("spawned render entity");
	}
	public void OnTickInGame(Minecraft mc)
	{
		if(mc.f!= lastworld)
		{
			//do spawny stuff here

			spawn(mc);
			lastworld = mc.f;
		}
		/*boolean found = false;
		for(int i=0; i<mc.f.b.size(); i++)
	    {
	        if(mc.f.b.get(i) instanceof RenderEntity) 
	            found=true;
	    }
		if (!found)
		{
			spawn(mc);
		}*/
	}
	@SuppressWarnings("unchecked")
	@Override
	public void AddRenderer(Map map) {
		System.out.println("Attaching worldeditcui renderer");
		map.put(RenderEntity.class, new RenderHooks());
	}
}
