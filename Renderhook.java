import net.minecraft.client.Minecraft;

public interface Renderhook {
    public void worldRender(Minecraft mc, float renderTick);
    // public void unrotatedRender(Minecraft mc, float renderTick);
}
