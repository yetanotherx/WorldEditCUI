package wecui.render.shapes;

import java.util.List;
import org.lwjgl.opengl.GL11;
import wecui.obfuscation.RenderObfuscation;
import wecui.render.LineColor;
import wecui.render.LineInfo;
import wecui.render.points.PointRectangle;

/**
 * Draws the top and bottom rings of
 * a polygon region
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render2DBox {

    protected RenderObfuscation obf = RenderObfuscation.getInstance();
    protected LineColor color;
    protected List<PointRectangle> points;
    protected int min;
    protected int max;

    public Render2DBox(LineColor color, List<PointRectangle> points, int min, int max) {
        this.color = color;
        this.points = points;
        this.min = min;
        this.max = max;
    }

    public void render() {
        double off = 0.03;
        for (LineInfo tempColor : color.getColors()) {
            tempColor.prepareRender();

            obf.startDrawing(GL11.GL_LINES);
            tempColor.prepareColor();

            for (PointRectangle point : points) {
                if (point != null) {
                    obf.addVertex(point.getPoint().getX() + 0.5, min + off, point.getPoint().getY() + 0.5);
                    obf.addVertex(point.getPoint().getX() + 0.5, max + 1 + off, point.getPoint().getY() + 0.5);
                }
            }
            obf.finishDrawing();
        }
    }
}
