package wecui;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import net.minecraft.src.Minecraft;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.NetHandler;
import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.WorldClient;
import wecui.event.ChannelEvent;
import wecui.event.WorldRenderEvent;
import wecui.render.region.CuboidRegion;

import com.mumfrey.liteloader.InitCompleteListener;
import com.mumfrey.liteloader.PluginChannelListener;
import com.mumfrey.liteloader.PostRenderListener;
import com.mumfrey.liteloader.core.LiteLoader;

public class LiteModWorldEditCUI implements InitCompleteListener, PluginChannelListener, PostRenderListener
{
    protected WorldEditCUI controller;
    protected WorldClient lastWorld;
    protected EntityPlayerSP lastPlayer;
    protected boolean gameStarted = false;
    public final static Charset UTF_8_CHARSET = Charset.forName("UTF-8");
    protected int entityUpdateTickCount = 0;
    protected WorldRenderEvent event;

	@Override
	public void init(File configPath)
	{
	}
	
	@Override
	public void upgradeSettings(String version, File configPath, File oldConfigPath)
	{
	}

    /* (non-Javadoc)
	 * @see com.mumfrey.liteloader.InitCompleteListener#onInitCompleted(net.minecraft.client.Minecraft, com.mumfrey.liteloader.core.LiteLoader)
	 */
	@Override
	public void onInitCompleted(Minecraft minecraft, LiteLoader loader)
	{
        this.controller = new WorldEditCUI(Minecraft.getMinecraft());
        this.controller.initialize();
		this.event = new WorldRenderEvent(this.controller);
	}

	@Override
	public void onLogin(NetHandler netHandler, Packet1Login loginPacket)
	{
		byte[] buffer = ("v|" + WorldEditCUI.protocolVersion).getBytes(UTF_8_CHARSET);
		LiteLoader.getInstance().sendPluginChannelMessage("WECUI", buffer);
	}

	@Override
	public List<String> getChannels()
	{
		return Arrays.asList(new String[] { "WECUI" });
	}

	@Override
	public void onCustomPayload(String channel, int length, byte[] data)
	{
        ChannelEvent channelevent = new ChannelEvent(this.controller, new String(data, UTF_8_CHARSET));
        this.controller.getEventManager().callEvent(channelevent);
	}

	@Override
	public void onTick(Minecraft mc, float partialTicks, boolean inGame, boolean clock)
	{
		if (inGame && clock && this.controller != null)
		{
	        if (mc.theWorld != this.lastWorld || mc.thePlayer != this.lastPlayer) {
	            this.lastWorld = mc.theWorld;
	            this.lastPlayer = mc.thePlayer;

	            if (!this.gameStarted) {
	                this.gameStarted = true;

	                new Updater(this.controller).start();
	                this.controller.setSelection(new CuboidRegion(this.controller));

//	                DataPacketList.register(this.controller);
	            }
	        }
		}
	}

	@Override
	public String getName()
	{
		return "WorldEditCUI by yetanotherx";
	}

	@Override
	public String getVersion()
	{
		return "1.6.2_00_lite";
	}

	@Override
	public void onPostRenderEntities(float partialTicks)
	{
        RenderHelper.disableStandardItemLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        this.event.setPartialTick(partialTicks);
        this.controller.getEventManager().callEvent(this.event);
        RenderHelper.enableStandardItemLighting();
	}

	@Override
	public void onPostRender(float partialTicks)
	{
		// TODO Auto-generated method stub
		
	}
}
