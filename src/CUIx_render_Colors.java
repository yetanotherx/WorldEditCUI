
import org.lwjgl.opengl.GL11;

/**
 * Default line colors
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIx_render_Colors {
    
    public static CUIx_render_LineInfo firstnormal = new CUIx_render_LineInfo(3.0f, 0.2f, 0.8f, 0.2f, 0.8f, GL11.GL_LESS);
    public static CUIx_render_LineInfo firsthidden = new CUIx_render_LineInfo(2.0f, 0.2f, 0.8f, 0.2f, 0.2f, GL11.GL_GEQUAL);

    public static CUIx_render_LineInfo secondnormal = new CUIx_render_LineInfo(3.0f, 0.2f, 0.2f, 0.8f, 0.8f, GL11.GL_LESS);
    public static CUIx_render_LineInfo secondhidden = new CUIx_render_LineInfo(2.0f, 0.2f, 0.2f, 0.8f, 0.2f, GL11.GL_GEQUAL);

    public static CUIx_render_LineInfo polynormal = new CUIx_render_LineInfo(3.0f, 0.2f, 0.8f, 0.8f, 0.8f, GL11.GL_LESS);
    public static CUIx_render_LineInfo polyhidden = new CUIx_render_LineInfo(2.0f, 0.2f, 0.8f, 0.8f, 0.2f, GL11.GL_GEQUAL);

    public static CUIx_render_LineInfo gridnormal = new CUIx_render_LineInfo(2.0f, 0.8F, 0.2F, 0.2F, 0.6f, GL11.GL_LESS);
    public static CUIx_render_LineInfo gridhidden = new CUIx_render_LineInfo(1f, 0.8F, 0.2F, 0.2F, 0.15f, GL11.GL_GEQUAL);

    public static CUIx_render_LineInfo boxnormal = new CUIx_render_LineInfo(3.0f, 0.8F, 0.2F, 0.2F, 0.8f, GL11.GL_LESS);
    public static CUIx_render_LineInfo boxhidden = new CUIx_render_LineInfo(2.0f, 0.8F, 0.2F, 0.2F, 0.2f, GL11.GL_GEQUAL);
    
}
