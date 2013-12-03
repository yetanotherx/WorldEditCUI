package wecui;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.server.S01PacketJoinGame;
import wecui.event.ChannelEvent;
import wecui.event.WorldRenderEvent;
import wecui.render.region.CuboidRegion;

import com.mumfrey.liteloader.InitCompleteListener;
import com.mumfrey.liteloader.PluginChannelListener;
import com.mumfrey.liteloader.PostRenderListener;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.core.PluginChannels;

public class LiteModWorldEditCUI implements InitCompleteListener, PluginChannelListener, PostRenderListener
{
    private static final String CHANNEL_WECUI = "WECUI";
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
	public void onLogin(INetHandler netHandler, S01PacketJoinGame loginPacket)
	{
		byte[] buffer = ("v|" + WorldEditCUI.protocolVersion).getBytes(UTF_8_CHARSET);
		PluginChannels.sendMessage(CHANNEL_WECUI, buffer);
	}

	@Override
	public List<String> getChannels()
	{
		return Arrays.asList(new String[] { CHANNEL_WECUI });
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

	                this.controller.setSelection(new CuboidRegion(this.controller));
	            }
	        }
		}
	}

	@Override
	public String getName()
	{
		return "WorldEditCUI";
	}

	@Override
	public String getVersion()
	{
		return "1.7.2";
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
	}
}
