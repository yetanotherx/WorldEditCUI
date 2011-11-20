/**
 * 
 */
package net.lahwran.wecui.rendering;

import net.lahwran.WorldRenderEvent;
import net.lahwran.fevents.Listener;
import net.lahwran.wecui.WorldEditCUI;
import net.lahwran.wecui.obf.ObfHub;

import org.lwjgl.opengl.GL11;


/**
 * @author lahwran
 *
 */
public class WorldRenderListener implements Listener<WorldRenderEvent> {

    private WorldEditCUI wecui;
    /**
     * @param worldEditCUI
     */
    public WorldRenderListener(WorldEditCUI wecui) {
        this.wecui = wecui;
    }

    @Override
    public void onEvent(WorldRenderEvent event) {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);
        GL11.glPushMatrix();
        ObfHub obf = wecui.getObfHub();
        GL11.glTranslated(-obf.getPlayerX(event.partialTick),
                          -obf.getPlayerY(event.partialTick),
                          -obf.getPlayerZ(event.partialTick));
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        if (wecui.getSelection() != null)
            wecui.getSelection().render();
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glPopMatrix();

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
    }

}
