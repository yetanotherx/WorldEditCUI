import java.util.Map;

import net.minecraft.client.Minecraft;


public class mod_Renderhook extends BaseMod {

	public mod_Renderhook()
	{
		ModLoader.getMinecraftInstance().s = new RenderHooks(ModLoader.getMinecraftInstance());
		//ModLoader.SetInGameHook(this, true, true); //the last true is because we don't want to iterate the entity list too often
	}
	
	@Override
	public String Version() {
		// TODO Auto-generated method stub
		return "1.3_01";
	}
	/*public static eb lastworld = null;
	public static EntityRenderhookdummy entity;
	public static void spawn(Minecraft mc)
	{
		entity = new EntityRenderhookdummy(mc.e);
		entity.c(mc.g.aJ, mc.g.aK, mc.g.aL);
		mc.e.a((pb)entity);
		entity.c(mc.g.aJ, mc.g.aK, mc.g.aL);
		System.out.println("spawning entity");
	}
	public void OnTickInGame(Minecraft mc)
	{
		if(mc.e!= lastworld)
		{
			//do spawny stuff here

			spawn(mc);
			lastworld = mc.e;
		}
		boolean found = false;
		for(int i=0; i<mc.e.b.size(); i++)
	    {
	        if(mc.e.b.get(i) instanceof EntityRenderhookdummy) 
	            found=true;
	    }
		if (!found)
		{
			spawn(mc);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void AddRenderer(Map map) {
		System.out.println("Attaching worldeditcui renderer");
		map.put(EntityRenderhookdummy.class, new RenderHooks());}*/
}
