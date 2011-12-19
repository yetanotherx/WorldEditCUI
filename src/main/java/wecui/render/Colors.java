package wecui.render;

import org.lwjgl.opengl.GL11;

/**
 * Default line colors
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class Colors {

    public static final LineInfo firstnormal = new LineInfo(3.0f, 0.2f, 0.8f, 0.2f, 0.8f, GL11.GL_LESS);
    public static final LineInfo firsthidden = new LineInfo(3.0f, 0.2f, 0.8f, 0.2f, 0.2f, GL11.GL_GEQUAL);
    public static final LineInfo secondnormal = new LineInfo(3.0f, 0.2f, 0.2f, 0.8f, 0.8f, GL11.GL_LESS);
    public static final LineInfo secondhidden = new LineInfo(3.0f, 0.2f, 0.2f, 0.8f, 0.2f, GL11.GL_GEQUAL);
    public static final LineInfo polynormal = new LineInfo(3.0f, 0.2f, 0.8f, 0.8f, 0.8f, GL11.GL_LESS);
    public static final LineInfo polyhidden = new LineInfo(3.0f, 0.2f, 0.8f, 0.8f, 0.2f, GL11.GL_GEQUAL);
    public static final LineInfo gridnormal = new LineInfo(3.0f, 0.8F, 0.2F, 0.2F, 0.4f, GL11.GL_LESS);
    public static final LineInfo gridhidden = new LineInfo(3.0f, 0.8F, 0.2F, 0.2F, 0.2f, GL11.GL_GEQUAL);
    public static final LineInfo boxnormal = new LineInfo(3.0f, 0.8F, 0.2F, 0.2F, 1.0f, GL11.GL_LESS);
    public static final LineInfo boxhidden = new LineInfo(2.0f, 0.4F, 0.1F, 0.1F, 0.2f, GL11.GL_GEQUAL);
}
