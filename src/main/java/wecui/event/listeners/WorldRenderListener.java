package wecui.event.listeners;

import wecui.fevents.Listener;
import wecui.obfuscation.Obfuscation;
import wecui.WorldEditCUI;
import org.lwjgl.opengl.GL11;
import wecui.event.WorldRenderEvent;
import wecui.obfuscation.RenderObfuscation;

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
        Obfuscation obf = controller.getObfuscation();
        RenderObfuscation robf = RenderObfuscation.getInstance();

        if (controller.getSelection() != null) {
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(false);
            GL11.glPushMatrix();
            
            obf.translateToCamera(event.getPartialTick());
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            
            try {
                controller.getSelection().render();
            } catch (Exception e) {
                e.printStackTrace();
            }

            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glPopMatrix();

            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
        }

        if (controller.getPreview().hasPreview()) {
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDepthMask(false);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
            GL11.glPolygonOffset(-1, -1);

            obf.translateToCamera(event.getPartialTick());
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            
            try {
                robf.setOpaque(128);
                controller.getPreview().render(event.getPartialTick());
                robf.setOpaque(255);
            } catch (Exception e) {
            }

            GL11.glDepthFunc(GL11.GL_LEQUAL);

            GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
        }
    }
}
