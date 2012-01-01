package wecui.render.shapes;

import org.lwjgl.opengl.GL11;
import wecui.obfuscation.RenderObfuscation;
import wecui.render.LineColor;
import wecui.render.LineInfo;
import wecui.render.points.PointContainer;
import wecui.render.points.PointCube;

/**
 * Draws the grid lines around a cylindrical region
 * 
 * @author yetanotherx
 */
public class RenderCylinderGrid {

    protected LineColor color;
    protected double radius;
    protected int minY;
    protected int maxY;
    protected RenderObfuscation obf = RenderObfuscation.getInstance();
    protected double centerX;
    protected double centerZ;

    public RenderCylinderGrid(LineColor color, PointCube center, double radius, int minY, int maxY) {
        this.color = color;
        this.radius = radius;
        this.minY = minY;
        this.maxY = maxY;
        this.centerX = center.getPoint().getX() + 0.5;
        this.centerZ = center.getPoint().getZ() + 0.5;
    }

    public void render() {
        for (LineInfo tempColor : color.getColors()) {
            tempColor.prepareRender();

            int tmaxY = maxY + 1;
            int tminY = minY;
            int posRadius = (int) Math.ceil(radius);
            int negRadius = (int) -Math.ceil(radius);

            for (double tempX = negRadius; tempX <= posRadius; ++tempX) {
                double tempZ = radius * Math.cos(Math.asin(tempX / radius));
                obf.startDrawing(GL11.GL_LINE_LOOP);
                tempColor.prepareColor();

                obf.addVertex(centerX + tempX, tmaxY, centerZ + tempZ);
                obf.addVertex(centerX + tempX, tmaxY, centerZ - tempZ);
                obf.addVertex(centerX + tempX, tminY, centerZ - tempZ);
                obf.addVertex(centerX + tempX, tminY, centerZ + tempZ);

                obf.finishDrawing();
            }

            for (double tempZ = negRadius; tempZ <= posRadius; ++tempZ) {
                double tempX = radius * Math.sin(Math.acos(tempZ / radius));
                obf.startDrawing(GL11.GL_LINE_LOOP);
                tempColor.prepareColor();

                obf.addVertex(centerX + tempX, tmaxY, centerZ + tempZ);
                obf.addVertex(centerX - tempX, tmaxY, centerZ + tempZ);
                obf.addVertex(centerX - tempX, tminY, centerZ + tempZ);
                obf.addVertex(centerX + tempX, tminY, centerZ + tempZ);

                obf.finishDrawing();
            }
        }
    }
}
