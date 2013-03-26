package wecui;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.NetHandler;
import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.WorldClient;
import wecui.Updater;
import wecui.WorldEditCUI;
import wecui.event.ChannelEvent;
import wecui.event.WorldRenderEvent;
import wecui.obfuscation.DataPacketList;
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
	public void init()
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
		this.event = new WorldRenderEvent(controller);
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
        ChannelEvent channelevent = new ChannelEvent(controller, new String(data, UTF_8_CHARSET));
        controller.getEventManager().callEvent(channelevent);
	}

	@Override
	public void onTick(Minecraft mc, float partialTicks, boolean inGame, boolean clock)
	{
		if (inGame && clock && controller != null)
		{
	        if (mc.theWorld != lastWorld || mc.thePlayer != lastPlayer) {
	            lastWorld = mc.theWorld;
	            lastPlayer = mc.thePlayer;

	            if (!gameStarted) {
	                gameStarted = true;

	                new Updater(controller).start();
	                this.controller.setSelection(new CuboidRegion(controller));

	                DataPacketList.register(controller);
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
		return "1.5.1_02_lite";
	}

	@Override
	public void onPostRenderEntities(float partialTicks)
	{
        RenderHelper.disableStandardItemLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        event.setPartialTick(partialTicks);
        controller.getEventManager().callEvent(event);
        RenderHelper.enableStandardItemLighting();
	}

	@Override
	public void onPostRender(float partialTicks)
	{
		// TODO Auto-generated method stub
		
	}
}
