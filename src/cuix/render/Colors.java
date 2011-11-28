package cuix.render;


import org.lwjgl.opengl.GL11;

/**
 * Default line colors
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class Colors {
    
    public static LineInfo firstnormal = new LineInfo(3.0f, 0.2f, 0.8f, 0.2f, 0.8f, GL11.GL_LESS);
    public static LineInfo firsthidden = new LineInfo(3.0f, 0.2f, 0.8f, 0.2f, 0.2f, GL11.GL_GEQUAL);

    public static LineInfo secondnormal = new LineInfo(3.0f, 0.2f, 0.2f, 0.8f, 0.8f, GL11.GL_LESS);
    public static LineInfo secondhidden = new LineInfo(3.0f, 0.2f, 0.2f, 0.8f, 0.2f, GL11.GL_GEQUAL);

    public static LineInfo polynormal = new LineInfo(3.0f, 0.2f, 0.8f, 0.8f, 0.8f, GL11.GL_LESS);
    public static LineInfo polyhidden = new LineInfo(3.0f, 0.2f, 0.8f, 0.8f, 0.2f, GL11.GL_GEQUAL);

    public static LineInfo gridnormal = new LineInfo(3.0f, 0.8F, 0.2F, 0.2F, 0.2f, GL11.GL_LESS);
    public static LineInfo gridhidden = new LineInfo(2.0f, 0.4F, 0.1F, 0.1F, 0.1f, GL11.GL_GEQUAL);

    public static LineInfo boxnormal = new LineInfo(3.0f, 0.8F, 0.2F, 0.2F, 1.0f, GL11.GL_LESS);
    public static LineInfo boxhidden = new LineInfo(2.0f, 0.4F, 0.1F, 0.1F, 0.2f, GL11.GL_GEQUAL);
    
}
