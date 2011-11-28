package cuix.event;


/**
 * 
 */
import cuix.fevents.Listener;
import cuix.obfuscation.ObfuscationHandler;
import cuix.CUIx;
import org.lwjgl.opengl.GL11;

/**
 * @author lahwran
 *
 */
public class WorldRenderListener implements Listener<WorldRenderEvent> {

    private CUIx cuix;

    public WorldRenderListener(CUIx cuix) {
        this.cuix = cuix;
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
            ObfuscationHandler obf = cuix.getObfuscation();

            GL11.glTranslated(-obf.getPlayerX(event.partialTick),
                    -obf.getPlayerY(event.partialTick),
                    -obf.getPlayerZ(event.partialTick));
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            if (cuix.getSelection() != null) {
                cuix.getSelection().render();
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
