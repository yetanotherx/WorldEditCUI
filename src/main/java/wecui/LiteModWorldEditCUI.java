package wecui;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.server.S01PacketJoinGame;
import wecui.event.ChannelEvent;
import wecui.event.WorldRenderEvent;
import wecui.gui.WorldEditCUIConfigPanel;
import wecui.render.region.CuboidRegion;

import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.InitCompleteListener;
import com.mumfrey.liteloader.PluginChannelListener;
import com.mumfrey.liteloader.PostRenderListener;
import com.mumfrey.liteloader.core.ClientPluginChannels;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.core.PluginChannels.ChannelPolicy;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.util.ModUtilities;

public class LiteModWorldEditCUI implements InitCompleteListener, PluginChannelListener, PostRenderListener, Configurable
{
    private static final String CHANNEL_WECUI = "WECUI";
    private final static Charset UTF_8_CHARSET = Charset.forName("UTF-8");
    
	private WorldEditCUI controller;
    private WorldClient lastWorld;
    private EntityPlayerSP lastPlayer;
    private boolean gameStarted = false;
    private WorldRenderEvent event;

    private KeyBinding keyBindToggleUI = new KeyBinding("wecui.keys.toggle", Keyboard.KEY_NONE, "wecui.keys.category");
    private KeyBinding keyBindClearSel = new KeyBinding("wecui.keys.clear", Keyboard.KEY_NONE, "wecui.keys.category");
    
    private boolean visible = true;
    
	@Override
	public void init(File configPath)
	{
		ModUtilities.registerKey(this.keyBindToggleUI);
		ModUtilities.registerKey(this.keyBindClearSel);
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
	public void onJoinGame(INetHandler netHandler, S01PacketJoinGame loginPacket)
	{
		this.visible = true;
		this.helo();
	}

	/**
	 * 
	 */
	private void helo()
	{
		byte[] buffer = ("v|" + WorldEditCUI.protocolVersion).getBytes(UTF_8_CHARSET);
		ClientPluginChannels.sendMessage(CHANNEL_WECUI, buffer, ChannelPolicy.DISPATCH_ALWAYS);
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
		if (inGame && mc.currentScreen == null)
		{
			if (this.keyBindToggleUI.isPressed())
			{
				this.visible = !this.visible;
			}
			
			if (this.keyBindClearSel.isPressed())
			{
				if (mc.thePlayer != null) mc.thePlayer.sendChatMessage("//sel");
			}
		}
		
		if (inGame && clock && this.controller != null)
		{
	        if (mc.theWorld != this.lastWorld || mc.thePlayer != this.lastPlayer) {
	            this.lastWorld = mc.theWorld;
	            this.lastPlayer = mc.thePlayer;

	            if (!this.gameStarted) {
	                this.gameStarted = true;

	                this.controller.setSelection(new CuboidRegion(this.controller));
	        		this.helo();
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
		return "1.7.2_02";
	}
	
	@Override
	public Class<? extends ConfigPanel> getConfigPanelClass()
	{
		return WorldEditCUIConfigPanel.class;
	}

	@Override
	public void onPostRenderEntities(float partialTicks)
	{
		if (this.visible && this.event != null)
		{
	        RenderHelper.disableStandardItemLighting();
	        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
	        this.event.setPartialTick(partialTicks);
	        this.controller.getEventManager().callEvent(this.event);
	        RenderHelper.enableStandardItemLighting();
		}
	}

	@Override
	public void onPostRender(float partialTicks)
	{
	}
	
	public WorldEditCUI getController()
	{
		return this.controller;
	}
}
