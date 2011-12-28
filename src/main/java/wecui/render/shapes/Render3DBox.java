package wecui.render.shapes;

import org.lwjgl.opengl.GL11;
import wecui.obfuscation.RenderObfuscation;
import wecui.render.LineColor;
import wecui.render.LineInfo;
import wecui.render.points.PointContainer;

/**
 * Draws a rectangular prism around 2 corners
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render3DBox {

    protected RenderObfuscation obf = RenderObfuscation.getInstance();
    protected LineColor color;
    protected PointContainer first;
    protected PointContainer second;

    public Render3DBox(LineColor color, PointContainer first, PointContainer second) {
        this.color = color;
        this.first = first;
        this.second = second;
    }

    public void render() {
        double x1 = first.getX();
        double y1 = first.getY();
        double z1 = first.getZ();
        double x2 = second.getX();
        double y2 = second.getY();
        double z2 = second.getZ();

        for (LineInfo tempColor : color.getColors()) {
            tempColor.prepareRender();

            // Draw bottom face
            obf.startDrawing(GL11.GL_LINE_LOOP);
            tempColor.prepareColor();
            obf.addVertex(x1, y1, z1);
            obf.addVertex(x2, y1, z1);
            obf.addVertex(x2, y1, z2);
            obf.addVertex(x1, y1, z2);
            obf.finishDrawing();

            // Draw top face
            obf.startDrawing(GL11.GL_LINE_LOOP);
            tempColor.prepareColor();
            obf.addVertex(x1, y2, z1);
            obf.addVertex(x2, y2, z1);
            obf.addVertex(x2, y2, z2);
            obf.addVertex(x1, y2, z2);
            obf.finishDrawing();

            // Draw join top and bottom faces
            obf.startDrawing(GL11.GL_LINES);
            tempColor.prepareColor();

            obf.addVertex(x1, y1, z1);
            obf.addVertex(x1, y2, z1);

            obf.addVertex(x2, y1, z1);
            obf.addVertex(x2, y2, z1);

            obf.addVertex(x2, y1, z2);
            obf.addVertex(x2, y2, z2);

            obf.addVertex(x1, y1, z2);
            obf.addVertex(x1, y2, z2);

            obf.finishDrawing();
        }
    }
}
