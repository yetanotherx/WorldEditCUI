package wecui.render;

import org.lwjgl.opengl.GL11;

/**
 * Stores color data for each type of line.
 * 
 * Each line has a normal line, and a hidden line.
 * The normal line has an alpha value of 0.8f, and
 * the hidden line has an alpha value of 0.2f. They
 * both have a thickness of 3.0f.
 * 
 * @author yetanotherx
 * @author lahwran
 */
public enum LineColor {

    CUBOIDGRID(0.8F, 0.2F, 0.2F),
    CUBOIDBOX(0.8F, 0.3F, 0.3F),
    CUBOIDPOINT1(0.2F, 0.8F, 0.2F), //33CC33
    CUBOIDPOINT2(0.2F, 0.2F, 0.8F), //3333CC
    POLYGRID(0.8F, 0.2F, 0.2F),
    POLYBOX(0.8F, 0.3F, 0.3F),
    POLYPOINT(0.2F, 0.8F, 0.8F), //33CCCC
    ELLIPSOIDGRID(0.8F, 0.3F, 0.3F),
    ELLIPSOIDCENTER(0.8F, 0.8F, 0.2F), //CCCC33
    CYLINDERGRID(0.8F, 0.3F, 0.3F),
    CYLINDERCENTER(0.8F, 0.2F, 0.8F); //CC33CC
    protected LineInfo normal;
    protected LineInfo hidden;

    private LineColor(float r, float g, float b) {
        normal = new LineInfo(3.0f, r, g, b, 0.8f, GL11.GL_LESS);
        hidden = new LineInfo(3.0f, r, g, b, 0.2f, GL11.GL_GEQUAL);
    }

    public LineInfo getHidden() {
        return hidden;
    }

    public LineInfo getNormal() {
        return normal;
    }

    public LineInfo[] getColors() {
        return new LineInfo[]{hidden, normal};
    }
}
