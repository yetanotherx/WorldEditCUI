package wecui.event.listeners;

import wecui.fevents.Listener;
import wecui.obfuscation.Obfuscation;
import wecui.WorldEditCUI;
import org.lwjgl.opengl.GL11;
import wecui.event.WorldRenderEvent;

/**
 * Listener for WorldRenderEvent
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class WorldRenderListener implements Listener<WorldRenderEvent> {

    private WorldEditCUI controller;

    public WorldRenderListener(WorldEditCUI controller) {
        this.controller = controller;
    }

    /**
     * Renders the current selection if it exists
     * @param event 
     */
    @Override
    public void onEvent(WorldRenderEvent event) {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        //GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);
        GL11.glPushMatrix();

        try {
            Obfuscation obf = controller.getObfuscation();

            GL11.glTranslated(-obf.getPlayerXGuess(event.getPartialTick()),
                    -obf.getPlayerYGuess(event.getPartialTick()),
                    -obf.getPlayerZGuess(event.getPartialTick()));
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            if (controller.getSelection() != null) {
                controller.getSelection().render();
            }
        } catch (Exception e) {
        }

        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glPopMatrix();

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        //GL11.glEnable(GL11.GL_ALPHA_TEST);
    }
}
