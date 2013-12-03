package wecui.render.shapes;

import java.util.List;

import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import wecui.render.LineColor;
import wecui.render.LineInfo;
import wecui.render.points.PointRectangle;

/**
 * Draws the grid for a polygon region
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render2DGrid {

    protected LineColor color;
    protected List<PointRectangle> points;
    protected int min;
    protected int max;

    public Render2DGrid(LineColor color, List<PointRectangle> points, int min, int max) {
        this.color = color;
        this.points = points;
        this.min = min;
        this.max = max;
    }

    public void render() {
        double off = 0.03;
        for (double height = this.min; height <= this.max + 1; height++) {
            this.drawPoly(height + off);
        }
    }

    protected void drawPoly(double height) {
    	Tessellator tess = Tessellator.instance;
        for (LineInfo tempColor : this.color.getColors()) {
            tempColor.prepareRender();

            tess.startDrawing(GL11.GL_LINE_LOOP);
            tempColor.prepareColor();
            for (PointRectangle point : this.points) {
                if (point != null) {
                    tess.addVertex(point.getPoint().getX() + 0.5, height, point.getPoint().getY() + 0.5);
                }
            }
            tess.draw();
        }
    }
}
